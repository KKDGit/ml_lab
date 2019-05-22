#!/bin/bash

CONSUMER_KEY=$(bash ~/utils/read-props.sh ~/utils/twitter.properties consumerKey)
CONSUMER_SECRET=$(bash ~/utils/read-props.sh ~/utils/twitter.properties consumerSecret)
ACCESS_TOKEN=$(bash ~/utils/read-props.sh ~/utils/twitter.properties accessToken)
ACCESS_TOKEN_SECRET=$(bash ~/utils/read-props.sh ~/utils/twitter.properties accessTokenSecret)

export CONSUMER_KEY
export CONSUMER_SECRET
export ACCESS_TOKEN
export ACCESS_TOKEN_SECRET

flume-ng agent --name TwitterAgent --conf /usr/hdp/current/flume-server/conf --conf-file /home/kranthidr/projects/ml_lab/spark-problems/flume-nd/twitter.conf -DpropertiesImplementation=org.apache.flume.node.EnvVarResolverProperties
From Version 1.8
---------------------------------------------------------------------------------
flume-ng agent --name TwitterAgent --conf /usr/hdp/current/flume-server/conf --conf-file /home/kranthidr/projects/ml_lab/spark-problems/flume-nd/twitter1.conf

----------------------------------------
flume-ng agent --name NetCatAgent --conf /usr/hdp/current/flume-server/conf --conf-file /home/kranthidr/projects/ml_lab/spark-problems/flume-nd/nc-file-hdfs.conf