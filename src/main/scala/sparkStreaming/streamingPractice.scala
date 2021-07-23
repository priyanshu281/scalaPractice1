package sparkStreaming
import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.expr
import org.apache.spark.streaming.{Seconds, StreamingContext}

object streamingPractice extends Serializable {
  @transient lazy val logger=Logger.getLogger(getClass.getName)
  def main(args: Array[String]): Unit = {
    val spark= SparkSession.builder()
      .master("local[3]")
      .appName("structured streaming Word Count")
      .config("spark.streaming.stopGracefullyOnShutdown","true")
      .config("spark.sql.codegen.wholeStage", false)
      .getOrCreate()


    val dtsreamLines=spark.readStream
      .format("socket")
      .option("host","localhost")
      .option("port","9999")
      .load()
    dtsreamLines.printSchema()
    val wordDF=dtsreamLines.select(expr("explode(split(value,' ')) as word"))
    val countsDF=wordDF.groupBy("word").count()

   val query=countsDF.writeStream
      .outputMode("complete")
      .format("console")
      .start()
    logger.info("LIstening to LOcalHOst:9999")
    query.awaitTermination()

   /* val ssc= new StreamingContext(spark.sparkContext,Seconds(2))
    val lines= ssc.textFileStream("C:/test/")
    lines.flatMap(_.split(" ")).map(x=> (x,1)).reduceByKey(_+_).print()
    ssc.start()
    ssc.awaitTermination()*/
  }

}
