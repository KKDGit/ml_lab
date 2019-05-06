name := "sd-streaming"

version := "0.1"

scalaVersion := "2.11.8"

val sparkVersion = "2.3.0.2.6.5.0-292"

resolvers := List("Hortonworks Releases" at "http://repo.hortonworks.com/content/repositories/releases/",
  "Jetty Releases" at "http://repo.hortonworks.com/content/repositories/jetty-hadoop/")

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "org.apache.spark" %% "spark-mllib" % sparkVersion,
  "org.apache.spark" %% "spark-graphx" % sparkVersion,
  "org.apache.spark" %% "spark-streaming" % sparkVersion
)
