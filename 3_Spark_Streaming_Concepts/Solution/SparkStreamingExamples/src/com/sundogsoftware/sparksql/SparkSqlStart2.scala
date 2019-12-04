package com.sundogsoftware.sparksql

import org.apache.spark.sql.{Dataset, Row, SparkSession}


object SparkSqlStart2 {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("Spark SQL basic example")
      .master("local[*]")
      .getOrCreate()

    // For implicit conversions like converting RDDs to DataFrames

    val df:Dataset[Row] = spark.read.json("people.json")

    // Register the DataFrame as a SQL temporary view
    df.createOrReplaceTempView("people")

    //Display all data
    val sqlDF = spark.sql("SELECT * FROM people")
    sqlDF.show()


    //Count the number of row
    val sqlDF2 = spark.sql("SELECT count(*) AS count FROM people")
    sqlDF2.show()

    //TODO: exercise - average age of people
    //TODO: exercise - count people by age
  }
}
