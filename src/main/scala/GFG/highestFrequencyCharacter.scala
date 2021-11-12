package GFG
import scala.util.control.Breaks._
//given a string abbcaacb
//Visa round 1 question
//output should be the character with highest frequency in case of tie it should be the one which comes before in actual string
object highestFrequencyCharacter {
  def main(args: Array[String]): Unit = {
  val res=getHighestFreqAlpha("abbcaacbccc")
  println("Ur output is "+res)
  }
  def getHighestFreqAlpha(text:String):Char={
    var myLi=text.groupBy(identity).mapValues(_.size).toSeq.sortWith(_._2 > _._2).toList
    println(myLi)
    val maxFreq=myLi(0)._2
    val myMp=myLi.filter(x=> x._2==maxFreq).toMap
    println("Map"+myMp)
    var res:Char='\0'
    breakable {
      for (i <- text) {
        if (myMp.contains(i)) {
         res=i
          break
        }
      }
    }

   // println(t)
    res
  }
}
