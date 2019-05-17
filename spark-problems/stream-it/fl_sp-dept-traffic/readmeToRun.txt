-----
1. jars are needed for flume spark integration
[kranthidr@gw02 dept-traffic]$ ls -ltr /usr/hdp/current/flume-server/lib/spark-streaming-flume*
-rw-r--r-- 1 root root  86045 Nov  2  2016 /usr/hdp/current/flume-server/lib/spark-streaming-flume-sink_2.10-1.6.3.jar
-rw-r--r-- 1 root root 103388 Nov  2  2016 /usr/hdp/current/flume-server/lib/spark-streaming-flume_2.10-1.6.3.jar

[kranthidr@gw02 dept-traffic]$ ls -ltr /usr/hdp/current/flume-server/lib/commons-lang*
-rw-r--r-- 1 root root 479881 Oct 13  2016 /usr/hdp/current/flume-server/lib/commons-lang3-3.5.jar
-rw-r--r-- 1 root root 430341 May 11  2018 /usr/hdp/current/flume-server/lib/commons-lang3-3.4.jar
-rw-r--r-- 1 root root 278901 May 11  2018 /usr/hdp/current/flume-server/lib/commons-lang-2.5.jar

[kranthidr@gw02 dept-traffic]$ ls -ltr /usr/hdp/current/flume-server/lib/scala*
-rw-r--r-- 1 root root 5665808 May 11  2018 /usr/hdp/current/flume-server/lib/scala-library-2.11.12.jar
----------------

flume-ng agent --name sdc --conf /usr/hdp/current/flume-server/conf --conf-file /home/kranthidr/projects/ml_lab/spark-problems/stream-it/fl_sp-dept-traffic/sdc.conf

--------
add above dependendencies in build.sbt

(i) Custom sink JAR: Download the JAR corresponding to the following artifact (or direct link).

 groupId = org.apache.spark
 artifactId = spark-streaming-flume-sink_2.11
 version = 2.3.0
 
(ii) Scala library JAR: Download the Scala library JAR for Scala 2.11.8. It can be found with the following artifact detail (or, direct link).

 groupId = org.scala-lang
 artifactId = scala-library
 version = 2.11.8
(iii) Commons Lang 3 JAR: Download the Commons Lang 3 JAR. It can be found with the following artifact detail (or, direct link).

 groupId = org.apache.commons
 artifactId = commons-lang3
 version = 3.5
 ----------
 
 spark-submit --jars $(echo /home/kranthidr/stream-jars/*.jar | tr ' ' ',') \
 --class FlumeStreamingDepartmentCount \
 --conf spark.ui.port=4444 \
 target/scala-2.11/*.jar yarn gw02.itversity.com 12341
 
 
 spark-submit --class FlumeStreamingDepartmentCount \
  --conf spark.ui.port=4444 \
  --jars /home/kranthidr/stream-jars/*.jar \
  target/scala-2.11/*.jar yarn gw02.itversity.com 12341
 
 "/home/kranthidr/.ivy2/cache/org.apache.spark/*flume_2.11/jars/*.jar,
 /home/kranthidr/.ivy2/cache/org.apache.spark/*flume-sink_2.11/jars/*.jar,
 /home/kranthidr/.ivy2/cache/org.apache.spark/*flume-ass*/jars/*.jar,
 /usr/hdp/current/flume-server/lib/commons-lang3-3.5.jar,
 /usr/hdp/current/flume-server/lib/flume-ng-kafka-sink*.jar,
 /usr/hdp/current/spark2-client/jars/spark-streaming_2.11-2.3.0.2.6.5.0-292.jar" \
  


