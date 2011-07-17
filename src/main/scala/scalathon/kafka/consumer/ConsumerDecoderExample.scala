package scalathon.kafka.consumer

import java.util.Properties
import java.io.{ByteArrayInputStream, DataInputStream}
import kafka.consumer.{Consumer, ConsumerConnector, ConsumerConfig}
import kafka.serializer.Decoder
import kafka.message.Message
import java.util.concurrent.Executors
import org.I0Itec.zkclient._
import kafka.utils.StringSerializer
import scalathon.kafka.producer.MemberRecord

object ConsumerDecoderExample {
  def main(args: Array[String]) {
    val props = new Properties
    props.put("zk.connect", "127.0.0.1:2181")
    props.put("groupid", "member-records-group")

    val topic = "member-records"
    val numConsumerStreams = 1

    val consumerConfig = new ConsumerConfig(props)
    val consumerConnector: ConsumerConnector = Consumer.create(consumerConfig)
    val topicMessageStreams = consumerConnector.createMessageStreams(Predef.Map(topic -> numConsumerStreams))

    val testTopicStreams = topicMessageStreams.get(topic).get

    val executor = Executors.newFixedThreadPool(10)
    val decoder = new MemberRecordDecoder

    for(stream <- testTopicStreams) {
      executor.execute(new Runnable() {
        override def run() {
          for(message <- stream) {
            // process message
            val record = decoder.toEvent(message)
            println("Record -> (id = " + record.memberId + ", name = " + record.name + ", location = " +
              record.location + ")")
          }
        }
      });
    }

    // attach shutdown handler to catch control-c
    Runtime.getRuntime().addShutdownHook(new Thread() {
      override def run() = {
        consumerConnector.shutdown
        executor.shutdownNow()
        tryCleanupZookeeper("127.0.0.1:2181", "member-records-group")
        println("consumer threads shutted down")
      }
    })
  }

  def tryCleanupZookeeper(zkUrl: String, groupId: String) {
    try {
      val dir = "/consumers/" + groupId
      println("Cleaning up temporary zookeeper data under " + dir + ".")
      val zk = new ZkClient(zkUrl, 30*1000, 30*1000, StringSerializer)
      zk.deleteRecursive(dir)
      zk.close()
    } catch {
      case _ => // swallow
    }
  }
}

class MemberRecordDecoder extends Decoder[MemberRecord] {
  def toEvent(message: Message):MemberRecord = {
    val inputStream = new ByteArrayInputStream(message.payload.array, message.payload.arrayOffset, message.payload.limit)
    val dataInputStream = new DataInputStream(inputStream)
    new MemberRecord(dataInputStream.readInt, dataInputStream.readUTF, dataInputStream.readUTF)
  }
}