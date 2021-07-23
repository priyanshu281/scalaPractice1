package GFG
//Larget sum contiguous sub-array
object kadenAlgo extends App {

  //val arr= Array(-13,-3,-25,-20,-3,-16,-23,-12,5,-22,-15,-4,-7)
  //val arr= Array(-13,-3,-25,-20,-3,-16,-23,-12,-5,-22,-15,-4,-7)  test case failing for all negative integers
  //val arr=Array(-2,-3,4,-1,-2,1,5,-3)
  //val arr=Array(-4,5,6,-2,2,10,-20,11)
 // val arr=Array(-5)
  val arr=Array(1,2,3,-30,15,-9,10,11,6,-2)
  var l=0
  var k=arr.length-1
  var biggest_sum_till_now= Integer.MIN_VALUE
    //print(findSum(2,2))
    while (k >= l) {
      var currentSum=findSum(l,k)
      if (currentSum > biggest_sum_till_now)
        biggest_sum_till_now =currentSum
      println("Running Sum"+currentSum)
      if(arr(l) < 0)
           l=l+1

      else if (arr(k) <0)
           k=k-1

      else if (arr(l+1) <0)
           l=l+1

      else if (arr(k-1) <0)
           k=k-1

      else if (arr(l)< arr(k))
           l=l+1
      else
           k=k-1
    }
  println("Biggest Sum for subarray:"+biggest_sum_till_now)
  /* use case match instead of if else ladder */

  def findSum(start:Int,end:Int):Int={
    var iters=end-start +1
    var sum=0

    for(i <- start to end){
      sum=sum+arr(i)
    }
    sum
  }
}
