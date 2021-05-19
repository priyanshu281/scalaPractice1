package scalaDFDSMediumplus

/* When to use Abstract Class
 1. When we want to construct a base class which needs constructor arguments.
 2. When our code will called from java code
 */

object abstractClassExample {
  def main(args: Array[String]): Unit = {
val u:myauthor=new CFG("Fatima","Quantum Entanglement")
  u.details() //would execute details
  u.authorHistory() //NOTE :didnt override this in CFG class
    u.authorAchievments()

    //This Shouldn't work as we cant instantiate myauthor
    var u1 = new myauthor("Sarra Siddiqee","How to tame your husband") {
      override def details(): Unit = {
        println("Next author ..")
      }
    }
    u1.details()
  }
 def showTotalPrice(): Unit ={
 val totPrice=new computePrice().calBookPrice(4,8.8)
   println(totPrice)
 }
}
abstract class myauthor(name:String,topic:String){
  def details() //Abstract method
  def authorHistory(): Unit ={ //NON ABSTRACT method
    print("Served in Indian Army")
  }
 final def authorAchievments(): Unit ={
    printf("BHARAT RATANA AWARDEE FOR")//+topic+ "is "+name)
  }
}
class CFG(name:String,topic:String) extends myauthor(name, topic){
  override def details(): Unit = {
    println("Overiding abstract method details")
    println("Author name"+name)
    println("Author's subject"+topic)
  }
}
abstract class bookPrice[Z]{
  def calBookPrice(quantity:Int,pricePerBook:Z):Z

}
class computePrice extends bookPrice[Double]{
  override def calBookPrice(quantity: Int, pricePerBook: Double): Double = pricePerBook * quantity
}