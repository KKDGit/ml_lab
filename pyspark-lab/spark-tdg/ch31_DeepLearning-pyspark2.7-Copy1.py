
# coding: utf-8

# In[2]:


import pyspark
from pyspark.sql import SparkSession
spark = SparkSession.builder.master("local[*]").appName("ch31_DeepLearning2.7").getOrCreate()

# .config("spark.driver.memory", "8g")\
# .config("spark.executor.memory", "3g")\
# .config("spark.executor.cores", "5")\
# .config("spark.executor.instances", "10")\


# This file is sourced when running various Spark programs.
# Copy it as spark-env.sh and edit that to configure Spark for your site.
# Options read in YARN client mode
#SPARK_EXECUTOR_INSTANCES="2" #Number of workers to start (Default: 2)
#SPARK_EXECUTOR_CORES="1" #Number of cores for the workers (Default: 1).
#SPARK_EXECUTOR_MEMORY="1G" #Memory per Worker (e.g. 1000M, 2G) (Default: 1G)
#SPARK_DRIVER_MEMORY="512M" #Memory for Master (e.g. 1000M, 2G) (Default: 512 Mb)


# In[3]:


sc = spark.sparkContext


# In[4]:


for x in sc._conf.getAll():
    if "/proxy/" in x[1]:
        print(x[1])


# In[18]:


# pip uninstall pandas tensorflow keras tensorframes tensorflowonspark kafka jieba Pillow sparkdl -y
# rm -rf ~/.cache/pip/*
# pip install --upgrade pandas tensorflow keras tensorframes tensorflowonspark kafka jieba Pillow sparkdl 

#from sparkdl import readImages

from pyspark.sql.functions import lit
from pyspark.ml.image import ImageSchema
from sparkdl.image import imageIO


# In[15]:


img_dir = '/user/kranthidr/dataSets/spark-guide/deep-learning-images/'
image_df = ImageSchema.readImages(img_dir)


# In[7]:


# COMMAND ----------
image_df.count()


# In[8]:


tulips_df = ImageSchema.readImages(img_dir + "/tulips").withColumn("label", lit(1))
tulips_df.count()


# In[16]:


daisy_df = imageIO.readImagesWithCustomFn(img_dir + "/daisy", decode_f=imageIO.PIL_decode).withColumn("label", lit(0))
daisy_df.count()


# In[ ]:


tulips_train, tulips_test = tulips_df.randomSplit([0.6, 0.4])
daisy_train, daisy_test = daisy_df.randomSplit([0.6, 0.4])


# In[ ]:


train_df = tulips_train.unionAll(daisy_train)
test_df = tulips_test.unionAll(daisy_test)


# In[ ]:


# COMMAND ----------
from pyspark.ml.classification import LogisticRegression
from pyspark.ml import Pipeline
from sparkdl import DeepImageFeaturizer


# In[ ]:


featurizer = DeepImageFeaturizer(inputCol="image", outputCol="features", modelName="InceptionV3")


# In[ ]:


lr = LogisticRegression(maxIter=1, regParam=0.05, elasticNetParam=0.3, labelCol="label")


# In[ ]:


    p = Pipeline(stages=[featurizer, lr])


# In[ ]:


p_model = p.fit(train_df)


# In[ ]: