package scalathon.kafka.consumer

import java.util.Properties
import kafka.consumer.{Consumer, ConsumerConnector, ConsumerConfig}
import java.util.concurrent.Executors
import org.I0Itec.zkclient._
import kafka.utils.StringSerializer

object ConsumerExample {
  def main(args: Array[String]) {
    val props = new Properties
    props.put("zk.connect", "127.0.0.1:2181")
    props.put("groupid", "test-group")

    // set the topic
    val topic = "test-topic"
    // set the number of consumer streams for this topic
    val partitions = 1

    val consumerConfig = new ConsumerConfig(props)
    val consumerConnector: ConsumerConnector = Consumer.create(consumerConfig)
    // create the consumer streams
    val topicMessageStreams = consumerConnector.createMessageStreams(Predef.Map(topic -> partitions))

    // get the streams for the "test-topic" topic
    val testTopicStreams = topicMessageStreams.get(topic).get

    val executor = Executors.newFixedThreadPool(partitions)

    for(stream <- testTopicStreams) {
      executor.execute(new Runnable() {
        override def run() {
          for(message <- stream) {
            // process message
            println("\nConsumed " + kafka.utils.Utils.toString(message.payload, "UTF-8"))
          }
        }
      });
    }

    // attach shutdown handler to catch control-c
    Runtime.getRuntime().addShutdownHook(new Thread() {
      override def run() = {
        consumerConnector.shutdown
        executor.shutdownNow()
        println("\nConsumer threads shutted down")
      }
    })

  }

}