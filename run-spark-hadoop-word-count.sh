#!/usr/bin/env bash
cp /Users/xinrui/tao/apps/github/spark-tutorial/WorldCount/target/spark-wordcount-1.0-SNAPSHOT.jar /Users/xinrui/tao/apps/github/spark-tutorial/docker/spark/spark-container-build/spark-app/spark-wordcount-1.0-SNAPSHOT.jar

#run spark cluster
cd docker/spark
docker-compose up --build -d

#run hadoop cluster
cd ./../hadoop
docker-compose up --build 

