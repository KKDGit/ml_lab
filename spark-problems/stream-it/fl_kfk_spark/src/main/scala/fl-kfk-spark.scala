import org.apache.spark.SparkConf
import org.apache.spark.streaming.{StreamingContext,Seconds}

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
    
//import kafka.serializer.StringDecoder

object StreamingDepartmentCount {
 def main(args: Array[String]) {
     
 val conf = new SparkConf().setAppName("flume kafka spark integration").setMaster(args(0))
 val ssc = new StreamingContext(conf, Seconds(30))

val kafkaParams = Map[String, Object](
  "bootstrap.servers" -> "wn01.itversity.com:6667,wn02.itversity.com:6667,wn03.itversity.com:6667",
  "key.deserializer" -> classOf[StringDeserializer],
  "value.deserializer" -> classOf[StringDeserializer],
  "group.id" -> "use_a_separate_group_id_for_each_stream",
  "auto.offset.reset" -> "latest",
  "enable.auto.commit" -> (false: java.lang.Boolean)
)

val topics = Array("kkd_flkfkspark")
     
val stream = KafkaUtils.createDirectStream[String, String](
  ssc,
  PreferConsistent,
  Subscribe[String, String](topics, kafkaParams)
)

 val messages = stream.map(record => record.value)
     
 val departmentMessages = messages.filter(msg => {
 val endPoint = msg.split(" ")(6)
 endPoint.split("/")(1) == "department"
 })
                           
 val departments = departmentMessages.
 map(rec => {
 val endPoint = rec.split(" ")(6)
 (endPoint.split("/")(2), 1)
 })
                           
 val departmentTraffic = departments.reduceByKey((total, value) => total + value)
                           
 departmentTraffic.saveAsTextFiles("/user/kranthidr/spark-problems/stream-it/fl-kfk-spark/cnt")

 ssc.start()
 ssc.awaitTermination()

 }
}