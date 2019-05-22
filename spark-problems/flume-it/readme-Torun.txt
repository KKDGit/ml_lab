//copy log4j.properties lo4j2.xml 
cp /usr/hdp/current/flume-server/conf/log4j.properties /home/kranthidr/projects/ml_lab/spark-problems/flume-it/
cp /usr/hdp/current/flume-server/conf/log4j2.xml /home/kranthidr/projects/ml_lab/spark-problems/flume-it/

//Change log file paths in both log4j2.xmls, log4j.properties ..changed permissions ...but not working --logs are not written

--------------------------------------------------------------------------------
flume-ng agent --name kkda1 --conf /home/kranthidr/projects/ml_lab/spark-problems/flume-it/nc-console-memory --conf-file /home/kranthidr/projects/ml_lab/spark-problems/flume-it/nc-console-memory/example.conf

nc localhost 12341
----------------------------------------------------------------------------
flume-ng agent --name kkda2 --conf /home/kranthidr/projects/ml_lab/spark-problems/flume-it/exec-hdfs-memory --conf-file /home/kranthidr/projects/ml_lab/spark-problems/flume-it/exec-hdfs-memory/example.conf


