#use Toree version 0.2.0 jar
jupyter toree install --kernel_name='Spark' --log-level=50 --spark_home=/usr/hdp/current/spark2-client --user --replace --spark_opts="--master yarn"

jupyter toree install --kernel_name='Spark' --log-level=50 --spark_home=/usr/hdp/current/spark2-client --user --replace --spark_opts="--master yarn --packages graphframes:graphframes:0.6.0-spark2.3-s_2.11,mysql:mysql-connector-java:5.1.6,org.xerial:sqlite-jdbc:3.23.1,org.mongodb.spark:mongo-spark-connector_2.11:2.3.0,com.datastax.spark:spark-cassandra-connector_2.11:2.3.1"

jupyter toree install --kernel_name='nsc' --log-level=50 --spark_home=/usr/hdp/current/spark2-client --toree_opts='--nosparkcontext' --user --replace

jupyter toree install --kernel_name='0609' --log-level=50 --spark_home=/usr/hdp/current/spark2-client --user --replace --spark_opts="--master yarn --jars file://${HOME}/gitRepos/aas/ch06-lsa/target/ch06-lsa-2.0.0-jar-with-dependencies.jar,file://${HOME}/gitRepos/aas/ch09-risk/target/ch09-risk-2.0.0-jar-with-dependencies.jar"

cp ~/.local/share/jupyter/kernels/spark_scala/lib/toree-assembly-0.2.0-incubating.jar ~/.local/share/jupyter/kernels/0609_scala/lib/toree-assembly-0.2.0-incubating.jar

rm ~/.local/share/jupyter/kernels/0609_scala/lib/*0.1.0*.jar

jupyter toree install --kernel_name='0708' --log-level=50 --spark_home=/usr/hdp/current/spark2-client --user --replace --spark_opts="--master yarn --jars file://${HOME}/gitRepos/aas/ch07-graph/target/ch07-graph-2.0.0-jar-with-dependencies.jar,file://${HOME}/gitRepos/aas/ch08-geotime/target/ch08-geotime-2.0.0-jar-with-dependencies.jar"

cp ~/.local/share/jupyter/kernels/spark_scala/lib/toree-assembly-0.2.0-incubating.jar ~/.local/share/jupyter/kernels/0708_scala/lib/toree-assembly-0.2.0-incubating.jar

rm ~/.local/share/jupyter/kernels/0708_scala/lib/*0.1.0*.jar

jupyter toree install --kernel_name='adam' --log-level=50 --spark_home=/usr/hdp/current/spark2-client --user --replace --spark_opts="--master yarn --conf spark.serializer=org.apache.spark.serializer.KryoSerializer --conf spark.kryo.registrator=org.bdgenomics.adam.serialization.ADAMKryoRegistrator --jars file://${HOME}/softwares/adam-distribution-spark2_2.11-0.24.0/repo/adam-assembly-spark2_2.11-0.24.0.jar"

cp ~/.local/share/jupyter/kernels/spark_scala/lib/toree-assembly-0.2.0-incubating.jar ~/.local/share/jupyter/kernels/adam_scala/lib/toree-assembly-0.2.0-incubating.jar

rm ~/.local/share/jupyter/kernels/adam_scala/lib/*0.1.0*.jar