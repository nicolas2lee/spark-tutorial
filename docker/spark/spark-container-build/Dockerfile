FROM nicolas2lee/spark-base
ADD ./spark-app /spark/spark-2.2.0-bin-hadoop2.7/spark-app
ADD ./test.txt /spark/spark-2.2.0-bin-hadoop2.7/test.txt
RUN mkdir  /tmp/spark-events
RUN /spark/spark-2.2.0-bin-hadoop2.7/sbin/start-history-server.sh

CMD /spark/spark-2.2.0-bin-hadoop2.7/bin/spark-shell
