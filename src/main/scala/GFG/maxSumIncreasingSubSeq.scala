package GFG

import scala.collection.mutable.ArrayBuffer

object maxSumIncreasingSubSeq {
  def main(args: Array[String]): Unit = {
    //val arr=Array(1,100,1,2,3,101,5,4) i=5 j=0
    //val arr=Array(10,5,4,3)
    //val arr=Array(3,4,5,10)
    val arr=Array(1,101,2,3,100,4,5)
    val result=getMaxSubSeq(arr)
     println(result)
  }
  def getMaxSubSeq(ar:Array[Int]):Int={
    val n=ar.length
    var inc_flag:Boolean=true
    var ab=new ArrayBuffer[Int]()

    for (i <- n-1 to 1 by -1){
      if (ar(i-1) > ar(i)){

        inc_flag=false
      }else if( ar(i-1) < ar(i) && !inc_flag){
        val tmp= ar(i)
        ab += tmp
        ab=ab.map(x=> x+ar(i-1))

        //ab.foreach(x=> println(x))
        inc_flag=true
      }else if (ar(i-1) < ar(i) && inc_flag){
       if (ab.isEmpty) ab +=ar(i)
        ab= ab.map(x=> x+ar(i-1))
       println("value of i :"+i)
      }
    }
    if (ab.isEmpty)
      ar(0)
    else
      ab.max
  }
}
