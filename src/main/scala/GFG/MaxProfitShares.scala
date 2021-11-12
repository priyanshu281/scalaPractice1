package GFG

object MaxProfitShares {
  def maxProfit(prices: Array[Int]): Int = {
    var j=prices.length-1
    var i=0
    var maxSoFar= -100
    var diff=0

    while(j>=i){


      if(prices(j-1)-prices(j)>0){

        j=j-1

      }

      if(prices(i)-prices(i+1)>0){
        i=i+1
      }
      println("diff"+diff)
      println("MAx:"+maxSoFar)
      diff=prices(j)-prices(i)
      if(diff>maxSoFar){
        maxSoFar=diff

      }
      j=j-1
    }

    return maxSoFar
  }

  def main(args: Array[String]): Unit = {
    var r =Array(7,1,5,3,6,4)
    println(maxProfit(r))
  }
}
