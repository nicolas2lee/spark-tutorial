FROM ubuntu
#install apt-add-repository command
RUN apt-get update
RUN apt-get install software-properties-common curl git -y

#install oracle java
RUN apt-get install --reinstall ca-certificates
RUN apt-get install -y software-properties-common
RUN add-apt-repository ppa:webupd8team/java -y && apt-get update
#update source
RUN echo "oracle-java8-installer shared/accepted-oracle-license-v1-1 select true" | debconf-set-selections
RUN echo "oracle-java8-installer shared/accepted-oracle-license-v1-1 seen true" | debconf-set-selections

RUN apt-get install oracle-java8-installer -y
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

#install hadoop
#RUN mkdir /usr/local/share/hadoop
#RUN curl http://apache.crihan.fr/dist/hadoop/common/hadoop-2.8.1/hadoop-2.8.1.tar.gz   
#RUN curl https://dist.apache.org/repos/dist/release/hadoop/common/hadoop-2.8.1/hadoop-2.8.1.tar.gz.asc
#RUN curl https://dist.apache.org/repos/dist/release/hadoop/common/KEYS
#RUN gpg --import KEYS
#RUN gpg --verify hadoop-2.8.1/hadoop-2.8.1.tar.gz.asc hadoop-2.8.1.tar.gz
#RUN tar xvz -C /usr/local/share/hadoop

#instead of installing haddop, copy the local file into contianer, make sure your hadoop binary is under ./../hadoop-binary
ADD ./hadoop-binary/hadoop-2.8.1 /hadoop/hadoop-2.8.1
#copy custom config file
RUN rm /hadoop/hadoop-2.8.1/etc/hadoop/core-site.xml /hadoop/hadoop-2.8.1/etc/hadoop/hdfs-site.xml /hadoop/hadoop-2.8.1/etc/hadoop/slaves
ADD ./hadoop-conf /hadoop/hadoop-2.8.1/etc/hadoop
ADD ./run.sh /hadoop/hadoop-2.8.1/run.sh
RUN chmod 777 /hadoop/hadoop-2.8.1/run.sh
RUN rm -rf /hadoop/hadoop-2.8.1/datanode
RUN mkdir /hadoop/hadoop-2.8.1/datanode
RUN mkdir /hadoop/hadoop-2.8.1/namenode

CMD ./hadoop/hadoop-2.8.1/bin/hadoop
