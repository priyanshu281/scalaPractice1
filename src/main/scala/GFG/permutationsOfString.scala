package GFG

object permutationsOfString {
  class permute {
    def permutation(str: String): Unit = {
      permutation("", str)
    }

    def permutation(prefix: String, str: String): Unit = {
      val n = str.length
      if (n == 0) println(prefix)
      else for (i <- 0 until n) {
        permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n))
      }
    }
  }

  def main(args: Array[String]): Unit = {

   var obj= new permute()
   obj.permutation("civil")
  }


}
