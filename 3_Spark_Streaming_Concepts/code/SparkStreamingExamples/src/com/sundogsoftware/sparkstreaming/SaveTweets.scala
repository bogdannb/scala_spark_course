package com.sundogsoftware.sparkstreaming

import org.apache.spark.streaming._
import org.apache.spark.streaming.twitter._
import Utilities._
import org.apache.spark.streaming.dstream.DStream
import twitter4j.Status

/** Listens to a stream of tweets and saves them to disk. */
object SaveTweets {
  
  /** Our main function where the action happens */
  def main(args: Array[String]) {

    // Configure Twitter credentials using twitter.txt
    setupTwitter()
    
    // Set up a Spark streaming context named "SaveTweets" that runs locally using
    // all CPU cores and one-second batches of data
    val ssc = new StreamingContext("local[*]", "SaveTweets", Seconds(1))
    
    // Get rid of log spam (should be called after the context is set up)
    setupLogging()

    // Create a DStream from Twitter using our streaming context
    val tweets: DStream[Status] = TwitterUtils.createStream(ssc, None)
    
    // Now extract the text of each status update into RDD's using map()
    val statuses: DStream[String] = tweets.map(status => status.getText())
    
    // Here's one way to just dump every partition of every stream to individual files:
    //statuses.saveAsTextFiles("Tweets", "txt")
    
    // But let's do it the hard way to get a bit more control.
    
    // Keep count of how many Tweets we've received so we can stop automatically
    // (and not fill up your disk!)
    var totalTweets:Long = 0
        
    statuses.foreachRDD((rdd, time) => {
      // Don't bother with empty batches
      if (rdd.count() > 0) {
        // Combine each partition's results into a single RDD:
        val repartitionedRDD = rdd.repartition(1).cache()
        // And print out a directory with the results.
        repartitionedRDD.saveAsTextFile("temp/Tweets_" + time.milliseconds.toString)
        // Stop once we've collected 1000 tweets.
        totalTweets += repartitionedRDD.count()
        println("Tweet count: " + totalTweets)
        if (totalTweets > 1000) {
          System.exit(0)
        }
      }
    })
    
    //EXERCISE: for the above block of code try to remove the repartition code and see what happens there (hint: delete the temp folder, run app and then look on the disk). 
    
    // You can also write results into a database of your choosing, but we'll do that later.
    
    // Set a checkpoint directory, and kick it all off
    ssc.checkpoint("temp/checkpoint/")
    ssc.start()
    ssc.awaitTermination()
  }  
}
