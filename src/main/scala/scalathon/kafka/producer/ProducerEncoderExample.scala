package scalathon.kafka.producer

import kafka.producer.ProducerConfig
import kafka.producer.Producer
import kafka.producer.ProducerData
import java.util.Properties
import kafka.serializer.Encoder
import kafka.message.Message
import java.nio.ByteBuffer
import java.io.ByteArrayOutputStream
import java.io.DataOutputStream

object ProducerEncoderExample {
  def main(args: Array[String]) {
    val props = new Properties
    props.put("zk.connect", "127.0.0.1:2181");
    props.put("serializer.class", "scalathon.kafka.producer.MemberRecordEncoder");
    val config = new ProducerConfig(props);
    val producer = new Producer[Message, MemberRecord](config);

    // send a single message
    val message = new MemberRecord(1, "John", "US")
    val producerData = new ProducerData[Message, MemberRecord]("member-records", message)
    producer.send(producerData)

    println("\nSent message " + message.toString + "\n")
    producer.close
  }

}

class MemberRecord(val memberId: Int, val name: String, val location: String) {
  override def toString = {
    "(" + memberId + "," + name + "," + location + ")"
  }
}

class MemberRecordEncoder extends Encoder[MemberRecord] {
  def toMessage(member: MemberRecord):Message = {
    val outputStream = new ByteArrayOutputStream()
    val dos = new DataOutputStream(outputStream)
    dos.writeInt(member.memberId)
    dos.writeUTF(member.name)
    dos.writeUTF(member.location)
    outputStream.flush

    new Message(outputStream.toByteArray)
  }

}