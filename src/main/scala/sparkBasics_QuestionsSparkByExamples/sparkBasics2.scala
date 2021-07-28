package sparkBasics_QuestionsSparkByExamples

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.{col, expr, when,sum,max,avg}


/**** WHEN CLUASE , GROUP BY CLAUSE  ****/

object sparkBasics2 {

  def main(args: Array[String]): Unit = {
   implicit val spark: SparkSession = SparkSession.builder()
      .master("local[1]")
      .appName("SparkByExamples.com")
      .getOrCreate()

    import spark.implicits._
    val data = List(("James","","Smith","36636","M",60000),
      ("Michael","Rose","","40288","M",70000),
      ("Robert","","Williams","42114","",400000),
      ("Maria","Anne","Jones","39192","F",500000),
      ("Jen","Mary","Brown","","F",0))

    val cols = Seq("first_name","middle_name","last_name","dob","sex","salary")
    val df:DataFrame = spark.createDataFrame(data).toDF(cols:_*)

    val df2=df.withColumn("New_sex",when($"sex" === "M","Male")
      .when($"sex" === "F", "Female")
      .otherwise("Trans")
    )
   // df2.show()

    val df3 = df.withColumn("new_sex",
      expr("case when sex = 'M' then 'Male' " +
        "when sex = 'F' then 'Female' " +
        "else 'Unknown' end"))
    val df4 = df.select(col("*"), when(col("sex") === "M","Male")
      .when(col("sex") === "F","Female")
      .otherwise("Unknown").alias("new_sex"))

    val df5 = df.select(col("*"),
      expr("case when sex = 'M' then 'Male' " +
        "when sex = 'F' then 'Female' " +
        "else 'Unknown' end").alias("new_sex"))

    /* ******* Group BY ******* */
    val simpledata = Seq(("James", "Sales","NY",9000,34,10000),
      ("Michael","Sales","NY",86000,56,20000),
      ("Robert","Sales","CA",81000,30,23000),
      ("Maria","Finance","CA",90000,24,23000),
      ("Raman","Finance","CA",99000,40,24000),
      ("Scott","Finance","NY",83000,36,19000),
      ("Jen","Finance","NY",79000,53,15000),
      ("Jeff","Marketing","CA",80000,25,18000),
      ("Kumar","Marketing","NY",91000,50,21000)
    )

    val dfgrpby = simpledata.toDF("employee_name","department","state","salary","age","bonus")

    dfgrpby.groupBy("department").max("salary")
    dfgrpby.groupBy("department","state")
      .sum("salary","bonus").show()

    /*** Agg() is used when many aggregates are to be done at a time ***/

    dfgrpby.groupBy("department")
      .agg(
        sum("salary").as("sum_salary"),
        avg("salary").as("avg_salary"),
        sum("bonus").as("sum_bonus"),
        max("bonus").as("max_bonus"))
      .show(false)

    /***   JOINS ***/

    val emp = Seq((1,"Smith",-1,"2018","10","M",3000),
      (2,"Rose",1,"2010","20","M",4000),
      (3,"Williams",1,"2010","10","M",1000),
      (4,"Jones",2,"2005","10","F",2000),
      (5,"Brown",2,"2010","40","",-1),
      (6,"Brown",2,"2010","50","",-1)
    )
    val empColumns = Seq("emp_id","name","superior_emp_id","year_joined",
      "emp_dept_id","gender","salary")

    val empDF = emp.toDF(empColumns:_*)
    empDF.show(false)

    val dept = Seq(("Finance",10),
      ("Marketing",20),
      ("Sales",30),
      ("IT",40)
    )

    val deptColumns = Seq("dept_name","dept_id")
    val deptDF = dept.toDF(deptColumns:_*)

    empDF.join(deptDF,empDF("emp_dept_id") === deptDF("dept_id"),"inner")

    /** in case of self join or joining columnname being same in two tables use Seq **/
    //val resultDf = PersonDF.join(empDF,Seq("personId"))


  }
}
