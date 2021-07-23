package GFG

object reverseThisString {
  def main(args: Array[String]): Unit = {
   println( reverseMe("airtel"))
   val arr= new Array[Int](30) //initialzie array with zeroes
   val arr1=Array[Int](30) // array of size 1 having element as 30


  }
  def reverseMe(str:String):String={
    val tmp= new StringBuilder("")

    for(i <- str.length-1 to 0 by -1 ){
       tmp +=str(i)


    }
    tmp.mkString
  }
}
