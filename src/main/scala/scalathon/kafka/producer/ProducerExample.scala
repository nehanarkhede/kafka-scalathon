package scalathon.kafka.producer

import java.util.Properties
import kafka.message.Message
import kafka.producer.{ProducerConfig, ProducerData, Producer}

object ProducerExample {
  def main(args: Array[String]) {
    // set the right config options
    val props = new Properties
    props.put("zk.connect", "127.0.0.1:2181");
    val config = new ProducerConfig(props);
    val producer = new Producer[Message, Message](config);
    // Kafka is not in the serialization business
    val topic = "Kafka-Novels"
    val data = "The Metamorphosis".getBytes
    val producerData =
      new ProducerData[Message, Message](topic,
                                         new Message(data))
    // send data to Kafka
    producer.send(producerData)
    producer.close
    println("Sent message -> test-message")
  }
}