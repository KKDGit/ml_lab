name := "flume-kafka-spark-sbt"

version := "0.1"

scalaVersion := "2.11.8"

val sparkVersion = "2.3.0.2.6.5.0-292"


resolvers ++= List("Hortonworks Releases" at "http://repo.hortonworks.com/content/repositories/releases/",
  "Jetty Releases" at "http://repo.hortonworks.com/content/repositories/jetty-hadoop/")

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-sql" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-mllib" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-graphx" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-streaming" % sparkVersion,
  "org.apache.spark" %% "spark-streaming-flume" % sparkVersion,
  "org.apache.spark" %% "spark-streaming-flume-sink" % sparkVersion,
  "org.apache.spark" %% "spark-streaming-flume-assembly" % sparkVersion,
  "org.apache.spark" %% "spark-streaming-kafka-0-10" % sparkVersion,
//"org.apache.spark" %% "spark-streaming-twitter" % "1.6.3"
//"org.apache.bahir" %% "spark-streaming-twitter" % "2.3.0",
  "org.scala-lang" % "scala-library" % "2.11.8",
  "org.apache.commons" % "commons-lang3" % "3.5" 
)

val ex_jars = List("slf4j-log4j12-1.7.16.jar","log4j-slf4j-impl-2.10.0.jar","joda-time-2.9.7.jar","log4j-api-2.10.0.jar")

assemblyExcludedJars in assembly := { 
  val cp = (fullClasspath in assembly).value
  cp filter {x => ex_jars.contains(x.data.getName)}
}

assemblyMergeStrategy in assembly := {
  case PathList("org","aopalliance", xs @ _*) => MergeStrategy.last
  case PathList("javax", "inject", xs @ _*) => MergeStrategy.last
  case PathList("javax", "servlet", xs @ _*) => MergeStrategy.last
  case PathList("javax", "activation", xs @ _*) => MergeStrategy.last
  case PathList("org", "apache", xs @ _*) => MergeStrategy.last
  case PathList("com", "google", xs @ _*) => MergeStrategy.last
  case PathList("com", "esotericsoftware", xs @ _*) => MergeStrategy.last
  case PathList("com", "codahale", xs @ _*) => MergeStrategy.last
  case PathList("com", "yammer", xs @ _*) => MergeStrategy.last
  case "about.html" => MergeStrategy.rename
  case "META-INF/ECLIPSEF.RSA" => MergeStrategy.last
  case "META-INF/mailcap" => MergeStrategy.last
  case "META-INF/mimetypes.default" => MergeStrategy.last
  case "plugin.properties" => MergeStrategy.last
  case "log4j.properties" => MergeStrategy.last
  case x => {
    val oldStrategy = (assemblyMergeStrategy in assembly).value 
    oldStrategy(x)
    }
}