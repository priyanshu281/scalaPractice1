package GFG

object anagramProblem {
  def main(args: Array[String]): Unit = {
    val str1 = "hui"
    val str2 = "jio"
    val res=areAnagram(str1.toCharArray,str2.toCharArray)
    println("result :"+res)


  }

  def areAnagram(charr1:Array[Char],charr2:Array[Char]):Boolean={
    val NO_OF_CHARS=256
    val testArr= new Array[Int](NO_OF_CHARS)
    if(charr1.length != charr2.length)
      false
    else{

      for( i <- charr1.indices){
        testArr(charr1(i) - 'a') =  testArr(charr1(i) - 'a') +1
        testArr(charr2(i) - 'a') = testArr(charr2(i) - 'a') -1

      }

      for(j <- testArr) {
        if (j != 0) {

          return false
        }

      }
      testArr.foreach(j =>println("j"+j))

      return  true

    }
  }
}
