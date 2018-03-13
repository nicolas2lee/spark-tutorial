package com.geekcap.javaworld.sparkexample;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


/**
 * Created by xinrui on 13/03/2018.
 */
public class WordCountTest {

    private WordCount wordCount;
    private JavaSparkContext javaSparkContext;

    @Before
    public void setUp() throws Exception {
        wordCount = new WordCount();
        SparkConf sparkConf = new SparkConf().setAppName("test").setMaster("local");
        javaSparkContext = new JavaSparkContext(sparkConf);

    }

    @Test
    public void should_return_word_count_result() throws Exception {
        File inputFile = new File(this.getClass().getResource("/test.txt").getFile());

        JavaPairRDD<String, Integer> result = wordCount.countWord(inputFile.getAbsolutePath(), javaSparkContext);

        result.map(x -> {
            if (x._1.equals("hello")){
                assertThat(x._2).isEqualTo(2);
                System.out.println(x._2);
            }
            return 0;
        }).collect();
    }
}