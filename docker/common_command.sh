docker cp /Users/xinrui/tao/apps/github/spark-tutorial/data/input/access.log hadoop_hadoop-namenode_1:/hadoop/hadoop-2.8.1/tempdata/access.log

docker exec -it hadoop_hadoop-namenode_1 bash

./bin/hdfs dfs -put /hadoop/hadoop-2.8.1/tempdata/access.log /app/loganalyzer/input/access.log