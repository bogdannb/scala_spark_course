package com.sundogsoftware.sparkstreaming

import org.apache.spark.streaming._
import org.apache.spark.streaming.twitter._
import Utilities._
import org.apache.spark.streaming.dstream.DStream
import twitter4j.Status

/** Simple application to listen to a stream of Tweets and print them out */
object PrintTweets {
 
  /** Our main function where the action happens */
  def main(args: Array[String]) {

    // Configure Twitter credentials using twitter.txt
    setupTwitter()
    

    // Set up a Spark streaming context named "PrintTweets" that runs locally using
    // all CPU cores and one-second batches of data
    val ssc = new StreamingContext("local[*]", "PrintTweets", Seconds(1))
    
    // Get rid of log spam (should be called after the context is set up)
    setupLogging()

    // Create a DStream from Twitter using our streaming context
    val tweets: DStream[Status] = TwitterUtils.createStream(ssc, None)
    
    // Now extract the text of each status update into RDD's using map()
    val statuses: DStream[String] = tweets.map(status => status.getText())
    
    // Print out the tweets
    statuses.print()

    //EXERCISE: Print the language of each tweet and hashtags (one per line)
    //Hint: use status.getHashTagEntities() for hash tags
    
    // Kick it all off
    ssc.start()
    ssc.awaitTermination()
  }  
}