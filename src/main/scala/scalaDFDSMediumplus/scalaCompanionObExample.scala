package scalaDFDSMediumplus

object scalaCompanionObExample {
  def main(args: Array[String]): Unit = {
  val myObj= new scalaCompanionObExample
    myObj.str2="sleepAndExerciseWell" //Able to access both private n protected from companion
    myObj.goodLifeStyle()
  val p1=new Person()
    p1.displayFinancialHealth()  // Not able to access protected n private parameters
    val p2=new Person
    p2.hasJob=false // able to access default parameter
    p2.displayFinancialHealth()
  }
}
class scalaCompanionObExample{
  var str1="EatWell"
  private var str2="SleepWell"
  private def goodLifeStyle(): Unit ={
    println(str1+"And "+str2)
  }

}
class Person{
  private var minimumIncomeNeeded:Double=30000
  protected var sourcesOfIncome:Int=3
  var hasJob:Boolean=true

  def displayFinancialHealth()={
    if(hasJob) {
      println("Person should have minimum income of  " + minimumIncomeNeeded)
      println("total Income Sources" + sourcesOfIncome)
    } else println("Nalla")
  }


}
