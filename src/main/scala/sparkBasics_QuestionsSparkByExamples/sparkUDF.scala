package sparkBasics_QuestionsSparkByExamples

import org.apache.spark.sql.SparkSession

object sparkUDF {
  def main(args: Array[String]): Unit = {
    implicit val spark: SparkSession = SparkSession.builder()
      .master("local[1]")
      .appName("SparkByExamples.com")
      .getOrCreate()
    import spark.implicits._
    val dataset = Seq((0, "hello"), (1, "world")).toDF("id", "text")
    val upper: String => String = _.toUpperCase
    import org.apache.spark.sql.functions.udf
    val upperUDF = udf(upper)
    dataset.withColumn("upper", upperUDF($"text")).show()
  }
}
