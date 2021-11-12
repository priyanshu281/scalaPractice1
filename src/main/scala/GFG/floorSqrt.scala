package GFG
import scala.util.control.Breaks._
object floorSqrt {
  def main(args: Array[String]): Unit = {
 println("GFG"+gfgSqrt(2147395600))
 println("My Sol:"+mySqrt(2147395600))
  }

  def gfgSqrt(x: Int): Int = {
    if (x == 0 || x == 1)
      return x;

    // Starting from 1, try all numbers until
    // i*i is greater than or equal to x.
    var  i = 1
    var result = 1;

    while (result <= x) {
      i=i+1;
      result = i * i;
    }
    return i - 1;
  }
  def mySqrt(x: Int): Int = {
    var maxSoFar:Long=0
    var prev=0
    var tmp:Long = 0
    if (x==0)
      return 0
    if (x==1)
      return 1
    breakable {
      for(i<- 1 to x/2 ){
        tmp=i*i
        if(tmp >maxSoFar && tmp<=x){
          maxSoFar=tmp
          prev=i}

        if(tmp> x)
          break

      }
    }

    return prev
  }
}
