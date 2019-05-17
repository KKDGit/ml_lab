-----
1. jars are needed for flume kafka spark integration in build.sbt

spark-streming
spark-streaming-flume
spark-streaming-flume-sink
spark-streaming-flume-assembly
spark-streaming-kakfa
scala-library
commons-lang3
----------------
just create a file named assembly.sbt in your SBT project directory with this line:

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.6")
 ----------
if -- sbt clean package -- fails 
rm -rf ~/.ivy2/cache
-------------
sbt assembly
----------------
2.
create fl-kfk-spark.conf

flume-ng agent --name wk --conf /usr/hdp/current/flume-server/conf --conf-file /home/kranthidr/projects/ml_lab/spark-problems/stream-it/fl_kfk_spark/fl-kfk-spark.conf
---
if kafka topic is not there --it will create a topic and successful in sending messaged but with message and in second time there is no error
---
kafka-topics --zookeeper nn01.itversity.com:2181,nn02.itversity.com:2181,rm01.itversity.com:2181 --list --topic kkd_flkfkspark
kafka-topics --zookeeper nn01.itversity.com:2181,nn02.itversity.com:2181,rm01.itversity.com:2181 --describe --topic kkd_flkfkspark
-----------------
kafka-console-consumer --bootstrap-server wn01.itversity.com:6667,wn02.itversity.com:6667,wn03.itversity.com:6667 --topic kkd_flkfkspark --from-beginning
---------------

3.
spark-submit --jars $(echo /home/kranthidr/stream-jars/*.jar | tr ' ' ',') \
--class StreamingDepartmentCount \
--conf spark.ui.port=4444 \
target/scala-2.11/*.jar yarn



