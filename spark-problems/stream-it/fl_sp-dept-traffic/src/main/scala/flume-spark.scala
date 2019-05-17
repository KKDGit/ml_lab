import org.apache.spark.SparkConf
import org.apache.spark.streaming.{StreamingContext,Seconds}
import org.apache.spark.streaming.flume._

object FlumeStreamingDepartmentCount {
  def main(args: Array[String]) {
      
    val conf = new SparkConf().setAppName("Flume Streaming Word Count").setMaster(args(0))
    val ssc = new StreamingContext(conf, Seconds(30))

    val stream = FlumeUtils.createPollingStream(ssc, args(1), args(2).toInt)
        
    val messages = stream.
      map(s => new String(s.event.getBody.array()))
        
    val departmentMessages = messages.
      filter(msg => {
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
    departmentTraffic.saveAsTextFiles("/user/kranthidr/spark-problems/stream-it/flume-spark/cnt")

    ssc.start()
    ssc.awaitTermination()

  }
}