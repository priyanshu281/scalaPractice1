package GFG
//Leetcode problem 27 coding- easy
object Solution {
  def main(args: Array[String]): Unit = {

    val ar=Array(0,4,4,0,4,4,4,0,2)
    def removeElement(nums: Array[Int], `val`: Int): Int = {
      var count = 0
      var ptr1 = 0
      var firstIf = 0
      var secondIf = 0
      var ptr2 = nums.length - 1
      while (ptr1 <= ptr2) {
      var str="akkhe"
        
        if (nums(ptr2) == `val`) {
          count = count + 1
          ptr2 = ptr2 - 1
          firstIf = firstIf + 1


        }
        if ((ptr2 > 0) && (nums(ptr2) != `val`) && (nums(ptr1) == `val`) ) {
          secondIf = secondIf + 1
          swap(nums, ptr1, ptr2)
          count = count + 1
          ptr1 = ptr1 + 1
          ptr2 = ptr2 - 1
        }
        if (nums(ptr1) != `val`)
          ptr1 = ptr1 + 1
      }
      print(count)
      println("first:" + firstIf)
      println("second:" + secondIf)
      //nums.foreach(p => println(p))
      nums.length - count
    }
     val left=removeElement(ar,4)

  }
  def swap(nums: Array[Int], pos1: Int, pos2: Int): Unit = {

    var tmp = nums(pos1)
    nums(pos1) = nums(pos2)
    nums(pos2) = tmp


  }
}
