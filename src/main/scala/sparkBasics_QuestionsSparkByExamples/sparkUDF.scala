package sparkBasics_QuestionsSparkByExamples
import scala.collection.mutable.ListBuffer
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{lit, when}

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
    //dataset.withColumn("upper", upperUDF($"text")).show()

    val datasetCricket= Seq(("Ind","Aus","Ind"),("Slk","Pak","Slk"),("Aus","Pak","Aus"),("Ind","Pak","Ind")).toDF("Team1","Team2","Winner")
    val Teamlist=List("Aus","Ind","Slk")//.toDF("PlayingTeam") //suppose to scan first two columns and then union the unique rows
    var teamlistDF=Teamlist.toDF("Country").withColumn("MatchesPlayed",lit(null))
    // val lst=Teamlist.collectAsList().toArray()


    for(team <- Teamlist){
      val cnt=datasetCricket.filter($"Team1" === team || $"Team2" === team).count()
      println("team "+team+" :"+cnt)
      teamlistDF=teamlistDF.withColumn("MatchesPlayed",when($"Country" === team,cnt).otherwise($"MatchesPlayed"))

    }
    teamlistDF.show()

      //team => datasetCricket.filter($"Team1" === team || $"Team2" === team).count())


     // val IndMatches = datasetCricket.filter($"Team1" === team || $"Team2" === team).count()
spark.close()
    }



}
