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

