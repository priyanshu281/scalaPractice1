
/*Spark Use Case – Olympics Data Analysis
Problem Statement 1: Find the total number of medals won by each country in swimming.
Problem Statement 2 : Find the number of medals that India won year wise.
Problem Statement 3: Find the total number of medals won by each country.*/

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

//import org.slf4j.{Logger, LoggerFactory}
import org.apache.log4j.{Level,Logger}


object OlympicsDataAnalysis extends App with Serializable {
 @transient lazy val LOG=Logger.getLogger(this.getClass) //For LOG4J
 //use @transient to not serialize LOG object

 /*val LOG=LoggerFactory.getLogger(this.getClass)
val root=LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME).asInstanceOf[Logger].setLevel(Level.INFO)*/
 //difficult to set logLevel in sl4j

 Logger.getLogger("org").setLevel(Level.ERROR)

 var conf=new SparkConf()
 conf=conf.set("spark.rdd.compress","true").set("spark.serializer","org.apache.spark.serializer.KryoSerializer")
 implicit  val spark:SparkSession=SparkSession.builder()
   .appName("OlympicsAnalysis")
   .enableHiveSupport()
   .config(conf)
   .master("local")
   .getOrCreate()
 spark.sparkContext.setLogLevel("ERROR")
 //This wont solve the purpose as this activates after half of the logs been published
 //Need to get log4j.properties from folder where spark is installed
 val olympicsSchema="athleteName String,Age Int,Country String,Year Int,ClosingDate Date,Sport String,Gold Int,Silver Int,Bronze Int,TotalMedals Int"
 val olyDF=spark.read
   .option("header","true")
   //.option("inferSchema","true") // Inferring Schema slows the performance,also date is parsed as string
   .schema(olympicsSchema) //schema can be provided in structType as well
   .option("dateFormat","dd-MM-yyyy") //without this it will give date format exception for the date columns
   .csv("C:/Users/abc/Documents/olympics_data1.csv")

 //olyDF.show(3)
 //LOG.error("printThis:"+olyDF.count())
import spark.implicits._
 olyDF.createOrReplaceTempView("olympicsData")
 val countryMedalsDF=spark.sql("""Select Country,SUM(TotalMedals) as MedalsWonTillDate from olympicsData where sport='Swimming' group by Country order by MedalsWonTillDate desc""")
 //countryMedalsDF.show(9)
 //olyDF.filter(olyDF.col("Sport") === "Swimming")
 olyDF.filter($"Sport" === "Swimming" && $"country" === "India")
 //to use dataframe apis you need to import implicits._

 val indiaTallyDF=spark.sql("select athleteName,TotalMedals from olympicsData where country='India' and Year='2012' ")

 //indiaTallyDF.show  //Total 6 Medals

 val totMedalsDF=spark.sql("""Select Country,SUM(TotalMedals) as MedalsWonTillDate from olympicsData group by Country order by MedalsWonTillDate desc""")
 totMedalsDF.show(true)
 spark.close()
}
