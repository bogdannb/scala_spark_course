package com.sundogsoftware.sparksql

import org.apache.spark.sql.{Dataset, Row, SparkSession}


object SparkSqlStart {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("Spark SQL basic example")
      .master("local[*]")
      .getOrCreate()

    // For implicit conversions like converting RDDs to DataFrames
    import spark.implicits._

    val df:Dataset[Row] = spark.read.json("people.json")

    // Displays the content of the DataFrame to stdout
    df.show()

    // Display schema of underlying data
    df.printSchema()

    // Select only the "name" column
    df.select("name").show()

    // Select people older than 21
    df.filter($"age" > 21).show()

    // Count people by age (HINT: use groupBy() first)
    df.groupBy("age").count().show()
  }
}
