package GFG
//Rearrange such that arrray[i] = i
object rearrangeBasedOnPosition {
  def main(args: Array[String]): Unit = {
  val arr= Array(7,9,0,3,5,2,4,22,6,98,21,10,56)
  println(rearrangeMe(arr).mkString(","))

  }
  def rearrangeMe(array: Array[Int]):Array[Int]={
    var j=array.length-1
    for(i <- 0 to array.length-1 ){
      if (array(i) < array.size && array(i) != i){
        val tmp=array(array(i))
        array(array(i)) = array(i)
        array(i)=tmp
      }
    }
    array
  }

}
