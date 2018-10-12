import subprocess
# result = subprocess.run(['last', '-FR'], stdout=subprocess.PIPE).stdout.decode("utf-8")
result = subprocess.check_output(['last', '-FR'])

users = result.split("\n")[:-3]

import re
import hashlib
def get_features(user):
    wordList = re.sub("[^\w]", " ",  user).split()
    mystring = wordList[0]
    hash_object = hashlib.md5(mystring.encode())
    date = wordList[4:10]
    date[0] = '10'
    #return [mystring, str(hash_object.hexdigest()), " ".join(wordList[3:10])]
    return [str(hash_object.hexdigest()), " ".join(date)]


hashed_logins = list(map(get_features,users))

import os
path = os.getenv("HOME") +"/data/mmt_data/"
spark_home = "/usr/hdp/current/spark2-client"
mode = "local[*]"

#import findspark
#findspark.init(spark_home)

#import pyspark
from pyspark.sql import SparkSession
spark = SparkSession.builder.master(mode).appName("userSessionsDataPrep").enableHiveSupport().getOrCreate()


import pandas as pd
import numpy as np

pdf = pd.DataFrame(data={"user": np.array(hashed_logins)[:,0], "login":np.array(hashed_logins)[:,1]})

df = spark.createDataFrame(pdf)

from pyspark.sql.functions import to_timestamp


df = df.withColumn("login_time", to_timestamp("login",'MM dd HH mm ss yyyy'))


spark.sql("""
CREATE DATABASE IF NOT EXISTS Kranthidr_db
LOCATION '/user/kranthidr/Kranthidr_db'
""")



spark.sql("""
USE Kranthidr_db
""")


new_df = spark.sql("""
select * from user_sessions
""")

to_store = df.select("user","login_time")
to_store = to_store.union(new_df)

from pyspark.sql.functions import first, count, col, expr

new_to_store = to_store.cube("user","login_time").agg(count("user"),count("login_time"),first("user"), first("login_time")).dropna().select(col("first(user, false)").alias("user"),col("first(login_time, false)").alias("login_time"))

new_to_store.write.mode("append").saveAsTable("Kranthidr_db.user_sessions1")

spark.sql("""
DROP TABLE Kranthidr_db.user_sessions
""")

spark.sql("""
ALTER TABLE Kranthidr_db.user_sessions1 RENAME TO Kranthidr_db.user_sessions
""")




