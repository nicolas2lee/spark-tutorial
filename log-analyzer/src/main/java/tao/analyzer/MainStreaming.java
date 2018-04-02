package tao.analyzer;

import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;
import scala.Tuple2;

import java.io.IOException;
import java.util.*;

/**
 * Created by xinrui on 11/03/2018.
 */
public class MainStreaming {
    public static void main(String[] args) {
        MainStreaming mainStreaming = new MainStreaming();
        final String input = "";
        SparkConf sparkConf = new SparkConf().setAppName("loganalyzer");
        JavaStreamingContext javaStreamingContext = mainStreaming.configJavaStreamingContext(sparkConf, 5);
        mainStreaming.setAnalyzeDAG(input, javaStreamingContext);
        javaStreamingContext.start();              // Start the computation
        try {
            javaStreamingContext.awaitTermination();   // Wait for the computation to terminate
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    JavaStreamingContext configJavaStreamingContext(SparkConf sparkConf, long batchDuration) {
        return new JavaStreamingContext(sparkConf, Durations.seconds(batchDuration));
    }

    void setAnalyzeDAG(String input, JavaStreamingContext javaStreamingContext) {
        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put("bootstrap.servers", "localhost:9092,anotherhost:9092");
        kafkaParams.put("key.deserializer", StringDeserializer.class);
        kafkaParams.put("value.deserializer", StringDeserializer.class);
        kafkaParams.put("group.id", "use_a_separate_group_id_for_each_stream");
        kafkaParams.put("auto.offset.reset", "latest");
        kafkaParams.put("enable.auto.commit", false);

        Collection<String> topics = Arrays.asList("topicA", "topicB");

        JavaInputDStream<ConsumerRecord<String, String>> stream =
                KafkaUtils.createDirectStream(
                        javaStreamingContext,
                        LocationStrategies.PreferConsistent(),
                        ConsumerStrategies.<String, String>Subscribe(topics, kafkaParams)
                );

        stream.mapToPair(record -> new Tuple2<>(record.key(), record.value()));
        JavaDStream<String> logData = javaStreamingContext.textFileStream(input);
        //logData.flatMap(line -> Collections.singleton(ApacheAccessLog.parseFromLogLine(line)).iterator());
        //JavaDStream<ApacheAccessLog> accessLogsDStream =
                logData.flatMap(
                line -> {
                    try {
                        return Collections.singleton(ApacheAccessLog.parseFromLogLine(line)).iterator();
                    } catch (IOException e) {
                        return Collections.emptyIterator();
                    }
                }
        ).print();
    }
}
