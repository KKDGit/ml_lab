//open webservice nc -lk <host> <port> and enter some text
//it is recieved by spark and it processes it and prints on console

import org.apache.spark.SparkConf
import org.apache.spark.streaming._

object StreamingWordCount {
    
def main(args: Array[String]) {

val conf = new SparkConf().setAppName("Streaming Word count").setMaster("yarn")
val ssc = new StreamingContext(conf, Seconds(10))
val lines = ssc.socketTextStream("gw02.itversity.com", 12341)
val words = lines.flatMap(line => line.split(" "))
val tuples = words.map(word => (word, 1))
val wordCount = tuples.reduceByKey((t, v) => t + v)
wordCount.print()
ssc.start()
ssc.awaitTermination()
    
    }
    
}