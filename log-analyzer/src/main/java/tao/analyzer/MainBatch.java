package tao.analyzer;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created by xinrui on 02/04/2018.
 */
public class MainBatch {
    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf().setAppName("loganalyzer");
        JavaSparkContext sc = new JavaSparkContext(sparkConf);

        JavaRDD<String> logData = sc.textFile(args[0]);
        System.out.println(String.format("There are %d lines", logData.count()));

        List<ApacheAccessLog> logLinesList = logData.flatMap(
                line -> {
                    try {
                        return Collections.singleton(ApacheAccessLog.parseFromLogLine(line)).iterator();
                    } catch (IOException e) {
                        return Collections.emptyIterator();
                    }
                }
        ).collect();
        logLinesList.stream()
                .forEach(System.out::println);

/*        JavaPairRDD<String, Integer> counts = textFile
                .flatMap(s -> Arrays.asList(s.split(" ")).iterator())
                .mapToPair(word -> new Tuple2<>(word, 1))
                .reduceByKey((a, b) -> a + b);
        //counts.saveAsTextFile("hdfs://...");
        */
    }
}
