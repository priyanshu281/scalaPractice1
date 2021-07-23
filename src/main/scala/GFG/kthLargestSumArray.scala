package GFG

import scala.collection.mutable

object kthLargestSumArray {

  def main(args: Array[String]): Unit = {
    val kth=args(0).toInt
    //val arr= Array(5,3,1,7,8,9,2,0,4,6)
    val arr= Array(10,-10,20,-40) //expected o/p -10   some bugs in te code
    def kthSumOfSubarray(ar:Array[Int], k:Int):Int={
    val pq=new mutable.PriorityQueue[Int]

    val sumArr= new Array[Int](ar.length+1)
    sumArr(0)=0
    sumArr(1)=ar(0)
    for(i <- 2 to ar.length){
      sumArr(i) = sumArr(i-1) + ar(i-1)
    }
      sumArr.foreach(x=> println(x))
    for(i <- 1 to ar.length){

      for(j<- i to ar.length){

        val x = sumArr(j) - sumArr(i-1)
        if (pq.size < k)
          pq.+=(x)
        else{
          if (pq.head < x){
            pq.dequeue()
            pq.+=(x)
          }

        }

      }

    }
    pq.dequeue()
    }


    println("Yr result is:"+kthSumOfSubarray(arr,kth))
  }

}
