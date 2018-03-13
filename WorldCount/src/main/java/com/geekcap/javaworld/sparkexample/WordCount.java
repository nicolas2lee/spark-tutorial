package com.geekcap.javaworld.sparkexample;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;


public class WordCount {


    void wordCountJava8( String filename ) {
        final String masterUrl = "spark://spark-master:7077";
        SparkConf conf = new SparkConf().setMaster(masterUrl).setAppName("Work Count App");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaPairRDD counts = countWord(filename, sc);

        counts.saveAsTextFile( "output" );
    }

    JavaPairRDD countWord(String filename, JavaSparkContext sc) {
        JavaRDD<String> input = sc.textFile( filename );
        JavaRDD<String> words = input.flatMap( s -> Arrays.asList( s.split( " " ) ).iterator() );
        return words.mapToPair( t -> new Tuple2( t, 1 ) ).reduceByKey( (x, y) -> (int)x + (int)y );
    }

    public static void main( String[] args ) {
        if( args.length == 0 ) {
            System.out.println( "Usage: WordCount <file>" );
            System.exit( 0 );
        }
        WordCount wordCount = new WordCount();
//        String filePath = "README.md";
        wordCount.wordCountJava8( args[0]);
    }
}
