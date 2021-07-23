package GFG
import scala.util.control.Breaks._
import GFG.kadenAlgo.arr

object findSubarrayWithGivenSum {
  def main(args: Array[String]): Unit = {
val key=args(0).toInt
var currentSum=0

val arr=Array(1,1,40,20,3,10,5,7,9)
var k=0
var l=0
val size=arr.length
    breakable {
      while (l < size && k < size) {
        currentSum = findSum(l, k, arr)
        if (currentSum < key)
          k = k + 1
        else if (currentSum > key) {
          l = l + 1
          k = l
        }
        else {
          arr.slice(l, k + 1).foreach(x => println(x))
          break
        }
      }
    }
println("NO SUBARRAY FOUND")
  }
  def findSum(start:Int,end:Int,array: Array[Int]):Int= {
    //var iters=end-start +1
    //print("l:"+start +"and k:"+end)
    var sum = 0
    if (end == 0)
      array(0)
    else {
      for (i <- start to end) {
        sum = sum + array(i)
      }
      sum
    }
  }
}
