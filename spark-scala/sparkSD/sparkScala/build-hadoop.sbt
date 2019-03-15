name := "sparkScala"

version := "0.1"

scalaVersion := "2.11.8"

//2.3.0.2.6.5.0-292
val sparkVersion = "2.3.0"
val hadoopVersion = "2.7.1"
//2.7.3.2.6.5.0-292

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "org.apache.spark" %% "spark-mllib" % sparkVersion,
  "org.apache.spark" %% "spark-graphx" % sparkVersion,

  "org.apache.hadoop" % "hadoop-common" % hadoopVersion,
  "org.apache.hadoop" % "hadoop-hdfs" % hadoopVersion,
  "org.apache.hadoop" % "hadoop-mapreduce-client-core" % hadoopVersion,
  "org.apache.hadoop" % "hadoop-streaming" % hadoopVersion,
  "org.apache.hadoop" % "hadoop-yarn-api" % hadoopVersion,
  "org.apache.hadoop" % "hadoop-yarn-common" % hadoopVersion,
  "org.apache.hadoop" % "hadoop-yarn-server-common" % hadoopVersion,
  "org.apache.hadoop" % "hadoop-yarn-server-nodemanager" % hadoopVersion,
  "org.apache.hadoop" % "hadoop-yarn-server-resourcemanager" % hadoopVersion,
  "org.apache.hadoop" % "hadoop-client" % hadoopVersion
)