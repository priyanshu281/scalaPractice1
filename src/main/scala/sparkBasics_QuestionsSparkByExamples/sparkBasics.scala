package sparkBasics_QuestionsSparkByExamples
import org.apache.spark.sql.functions.{array_contains, col}
import org.apache.spark.sql.types.{ArrayType, StringType, StructType}
import org.apache.spark.sql.{Row, SparkSession}



object sparkBasics {
  def main(args: Array[String]): Unit = {

   val spark:SparkSession=SparkSession.builder()
     .master("local")
     .appName("SparkByExamples-Basic Statements")
     .getOrCreate()

   val data = Seq(("James","Smith","USA",1),
     ("Michael","Rose","USA",2),
     ("Robert","Williams","USA",1),
     ("Maria","Jones","USA",3)
   )
   val columns = Seq("FirstName","LastName","Counry","StateId")
import spark.implicits._
    val df= data.toDF(columns:_*)
    df.select($"firstName",$"lastname").show(false)
    val columnsAll=df.columns.map(m=>col(m))
    df.select(columnsAll:_*).show()
    // OR samething can be written as
    df.select(columns.map(m => col(m)):_*)
    //Show columns from start and end index
    df.select(df.columns.slice(2,4).map(m=>col(m)):_*).show(3)
    val data2 = Seq(Row(Row("James","","Smith"),"OH","M"),
      Row(Row("Anna","Rose",""),"NY","F"),
      Row(Row("Julia","","Williams"),"OH","F"),
      Row(Row("Maria","Anne","Jones"),"NY","M"),
      Row(Row("Jen","Mary","Brown"),"NY","M"),
      Row(Row("Mike","Mary","Williams"),"OH","M")
    )
    val schema = new StructType()
      .add("name",new StructType()
        .add("firstname",StringType)
        .add("middlename",StringType)
        .add("lastname",StringType)
      )
      .add("state",StringType)
      .add("sex",StringType)

    val df2= spark.createDataFrame(spark.sparkContext.parallelize(data2),schema)
    df2.select("name.firstname","name.lastname").show(false)
    df2.select("name.*").show(false)

    df.withColumn("salary", $"stateId" * 10000).show()

                  /* *** FILTER/WHERE CLAUSE *** */

    val arrayStructData= Seq(
      Row(Row("James","","Smith"),List("Java","Scala","C++"),"OH","M"),
      Row(Row("Anna", "Rose", ""), List("Spark", "Java", "C++"), "NY", "F"),
      Row(Row("Julia", "", "Williams"), List("CSharp", "VB"), "OH", "F"),
      Row(Row("Maria", "Anne", "Jones"), List("CSharp", "VB"), "NY", "M"),
      Row(Row("Jen", "Mary", "Brown"), List("CSharp", "VB"), "NY", "M"),
      Row(Row("Mike", "Mary", "Williams"), List("Python", "VB"), "OH", "M")
    )

    val arrayStructureSchema= new StructType()
      .add("name", new StructType()
        .add("firstname",StringType)
        .add("middlename",StringType)
        .add("lastName",StringType)
      ).add("languages",ArrayType(StringType))
      .add("state",StringType)
      .add("gender", StringType)

    val languageDf= spark.createDataFrame(spark.sparkContext.parallelize(arrayStructData),arrayStructureSchema)
    //languageDf.show()

    //languageDf.filter($"name.lastname" === "Williams").show(false)

    languageDf.filter(array_contains($"languages","Java")).show(false)


    //  println("my sequence:"+k)

    val r= scala.util.Random
    val population:List[List[Int]] = for{
      d <- (0 to 1000).toList
      i <- 1 to 2

    } yield List(d,i)

    val dftest= population.map(x=> (x(0),x(1))).toDF(Seq("dummy_key","suffix"):_*)
    dftest.show()
    spark.close()

    //to remove header from rdd
    val rdd=spark.sparkContext.textFile("c://")
   /* val newrdd=rdd.mapPartitionWithIndex{
      (idx,iter) => if (idx == 0) iter.drop(1) else iter
    }*/
  }
}
