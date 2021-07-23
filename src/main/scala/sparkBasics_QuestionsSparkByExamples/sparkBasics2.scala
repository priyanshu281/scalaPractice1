package sparkBasics_QuestionsSparkByExamples

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, expr, when}

/**** WHEN CLUASE , GROUP BY CLAUSE  ****/

object sparkBasics2 {

  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession.builder()
      .master("local[1]")
      .appName("SparkByExamples.com")
      .getOrCreate()

    import spark.sqlContext.implicits._
    val data = List(("James","","Smith","36636","M",60000),
      ("Michael","Rose","","40288","M",70000),
      ("Robert","","Williams","42114","",400000),
      ("Maria","Anne","Jones","39192","F",500000),
      ("Jen","Mary","Brown","","F",0))

    val cols = Seq("first_name","middle_name","last_name","dob","sex","salary")
    val df = spark.createDataFrame(data).toDF(cols:_*)
    import spark.sqlContext.implicits._

    val df2=df.withColumn("New_gender",when($"sex" === "M","Male")
      .when($"sex" === "F", "Female")
      .otherwise("Trans")
    )
   // df2.show()

    val df3 = df.withColumn("new_gender",
      expr("case when gender = 'M' then 'Male' " +
        "when gender = 'F' then 'Female' " +
        "else 'Unknown' end"))
    val df4 = df.select(col("*"), when(col("gender") === "M","Male")
      .when(col("gender") === "F","Female")
      .otherwise("Unknown").alias("new_gender"))

    val df5 = df.select(col("*"),
      expr("case when gender = 'M' then 'Male' " +
        "when gender = 'F' then 'Female' " +
        "else 'Unknown' end").alias("new_gender"))

    /* ******* Group BY ******* */



  }


}
