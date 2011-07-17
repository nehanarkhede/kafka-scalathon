package scalathon.kafka.producer

import kafka.producer.ProducerConfig
import kafka.producer.Producer
import kafka.producer.ProducerData
import java.util.Properties

object BasicProducerExample {
  def main(args: Array[String]) {
    val options = Array("1. Send single message", "2. Send multiple messages")
    var continue: Boolean = true
    while(continue) {
      options.foreach(o => println(o))
      val option = readLine()
      continue = option != null
      if(continue) {
        option match {
          case "1" =>
            sendSingleMessage
          case "2" =>
            sendMultipleMessages
          case _ =>
            println("ERROR. Invalid option. Exiting...")
            continue = false
        }
      }
    }
  }

  def sendSingleMessage {
    val props = new Properties
    props.put("zk.connect", "127.0.0.1:2181")
    props.put("serializer.class", "kafka.serializer.StringEncoder")
    val config = new ProducerConfig(props)
    val producer = new Producer[String, String](config)

    // send a single message
    val producerData = new ProducerData[String, String]("test-topic", "test-message")
    producer.send(producerData)
    producer.close
    println("\nSent message -> test-message\n")
  }

  def sendMultipleMessages {
    val props = new Properties
    props.put("zk.connect", "127.0.0.1:2181")
    props.put("serializer.class", "kafka.serializer.StringEncoder")
    val config = new ProducerConfig(props)
    val producer = new Producer[String, String](config)

    // send a single message
    val messages = Array("test-message1", "test-message2")
    val producerData = new ProducerData[String, String]("test-topic", messages)

    producer.send(producerData)
    producer.close
    println("\nSent messages -> test-message1, test-message2\n")
  }
}