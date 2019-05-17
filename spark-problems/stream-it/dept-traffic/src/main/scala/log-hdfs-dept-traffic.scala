//open webservice tail_logs.sh | nc -lk <host> <port> and enter some text
//it is recieved by spark and it processes it and puts it in hdfs

import org.apache.spark.SparkConf
import org.apache.spark.streaming._

object StreamingDepartmentCount {
 def main(args: Array[String]) {

 val conf = new SparkConf().setAppName("Streaming Word Count").setMaster(args(0))
 val ssc = new StreamingContext(conf, Seconds(30))
 val messages = ssc.socketTextStream(args(1), args(2).toInt)

 val departmentMessages = messages.filter(msg => {
 val endPoint = msg.split(" ")(6)
 endPoint.split("/")(1) == "department"
 })

 val departments = departmentMessages.
 map(rec => {
 val endPoint = rec.split(" ")(6)
 (endPoint.split("/")(2), 1)
 })

 val departmentTraffic = departments.
 reduceByKey((total, value) => total + value)

 departmentTraffic.saveAsTextFiles("/user/kranthidr/spark-problems/stream-it/deptwisetraffic/cnt")

 ssc.start()
 ssc.awaitTermination()
 }
}

