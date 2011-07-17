package scalathon.kafka.producer

import kafka.producer.ProducerConfig
import kafka.producer.Producer
import kafka.producer.ProducerData
import kafka.producer.Partitioner
import java.util.Properties

object ProducerPartitionerExample {
  def main(args: Array[String]) {
    val props = new Properties
    props.put("zk.connect", "127.0.0.1:2181");
    props.put("serializer.class", "scalathon.kafka.producer.MemberRecordEncoder");
    props.put("partitioner.class", "scalathon.kafka.producer.MemberLocationPartitioner")

    val config = new ProducerConfig(props);
    val producer = new Producer[String, MemberRecord](config);

    // send a single message
    val recordsUS = Array[MemberRecord](new MemberRecord(1, "John", "US"), new MemberRecord(2, "Joe", "US"))
    val dataUS = new ProducerData[String, MemberRecord]("member-records", "US", recordsUS)

    producer.send(dataUS)
    println("\nSent messages with key = US to topic member-records\n")

    val recordsEU = Array[MemberRecord](new MemberRecord(3, "John", "Europe"), new MemberRecord(4, "Joe", "Europe"))
    val dataEU = new ProducerData[String, MemberRecord]("member-records", "EUR", recordsEU)

    producer.send(dataEU)
    println("\nSent messages with key = EUR to topic member-records\n")
    producer.close
  }
}

class MemberLocationPartitioner extends Partitioner[String] {
  def partition(location: String, numPartitions: Int): Int = {
    val ret = location.hashCode % numPartitions
    ret
  }
}