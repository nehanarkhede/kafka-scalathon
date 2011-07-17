package scalathon.kafka.consumer

import java.util.Properties
import kafka.consumer.{Consumer, ConsumerConnector, ConsumerConfig}
import java.util.concurrent.Executors
import org.I0Itec.zkclient._
import kafka.utils.StringSerializer

object ConsumerExampleSingleThread {
  def main(args: Array[String]) {
    // set the right config options
    val props = new Properties
    props.put("zk.connect", "127.0.0.1:2181")
    props.put("groupid", "kafka-fans")

    val topic = "Kafka-Novels"
    // pull all the Novels in ONE stream
    val partitions = 1

    val consumerConfig = new ConsumerConfig(props)
    val consumerConnector: ConsumerConnector =
      Consumer.create(consumerConfig)
    // create the message streams for topic Novels
    val topicMessageStreams =
      consumerConnector.createMessageStreams(Predef.Map(topic -> partitions))

    val novelsTopicStreams = topicMessageStreams.get(topic).get
    // we just have one novel topic stream
    val novelsTopicStream = novelsTopicStreams.head
    for(novel <- novelsTopicStream)
      println("Woohoo ! Time to read " + kafka.utils.Utils.toString(novel.payload, "UTF-8"))
  }
}