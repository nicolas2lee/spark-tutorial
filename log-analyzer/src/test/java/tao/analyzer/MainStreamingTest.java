package tao.analyzer;


import org.apache.spark.SparkConf;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * Created by xinrui on 14/03/2018.
 */
public class MainStreamingTest {
    private MainStreaming mainStreaming;
    private JavaStreamingContext javaStreamingContext;

    @Before
    public void setUp() throws Exception {
        mainStreaming = new MainStreaming();
        SparkConf sparkConf = new SparkConf().setAppName("loganalyzerTest").setMaster("local");
        javaStreamingContext = mainStreaming.configJavaStreamingContext(sparkConf, 5);
        /*mainStreaming.setAnalyzeDAG(input, javaStreamingContext);
        javaStreamingContext.start();              // Start the computation
        try {
            javaStreamingContext.awaitTermination();   // Wait for the computation to terminate
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    @Test
    public void should_return_in_dag() throws Exception {
/*        File inputFile = new File(this.getClass().getResource("/access_log").getFile());
        mainStreaming.setAnalyzeDAG(inputFile.getAbsolutePath(), javaStreamingContext);

        javaStreamingContext.start();              // Start the computation
        try {
            javaStreamingContext.awaitTermination();   // Wait for the computation to terminate
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

    }
}