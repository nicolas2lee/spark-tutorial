package tao.analyzer;

import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

import java.util.Collections;

/**
 * Created by xinrui on 11/03/2018.
 */
public class Main {
    public static void main(String[] args) {
        final String input = "";
        SparkConf sparkConf = new SparkConf().setAppName("loganalyzer");
        JavaStreamingContext javaStreamingContext = new JavaStreamingContext(sparkConf, Durations.seconds(5));
        JavaDStream<String> logData = javaStreamingContext.textFileStream(input);
        logData.flatMap(line -> Collections.singleton(ApacheAccessLog.parseFromLogLine(line)).iterator());
    }
}
