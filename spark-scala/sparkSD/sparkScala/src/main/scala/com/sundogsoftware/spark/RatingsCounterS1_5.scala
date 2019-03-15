package com.sundogsoftware.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._

/** Count up how many of each star rating exists in the MovieLens 100K data set. */
object RatingsCounterS1_5 {
 
  /** Our main function where the action happens */
  def main(args: Array[String]) {

    System.setProperty("hadoop.home.dir", "C:\\Spark\\spark-hadoop\\hadoop")

    // Set the log level to only print errors
   Logger.getLogger("org").setLevel(Level.ERROR)
        
    // Create a SparkContext using every core of the local machine, named RatingsCounter
  val sc = new SparkContext(master = "local[*]", "RatingsCounter")

    println(s"Spark Version :- ${sc.version}")
    // Load up each line of the ratings data into an RDD
    val path = "D:\\Learn\\dataSets\\ml-100k\\u.data"
    //val lines = sc.textFile(path = args(1))
    val lines = sc.textFile(path)
    
    // Convert each line to a string, split it out by tabs, and extract the third field.
    // (The file format is userID, movieID, rating, timestamp)
    val ratings = lines.map(x => x.toString().split("\t")(2))
    
    // Count up how many times each value (rating) occurs
    val results = ratings.countByValue()
    
    // Sort the resulting map of (rating, count) tuples
    val sortedResults = results.toSeq.sortBy(_._1)
    
    // Print each result on its own line.
    sortedResults.foreach(println)

    //ratings.saveAsTextFile(args(2))
    sc.stop()

  }
}
