package scalaDFDSMediumplus

object scalaConstructorExample {
  def main(args: Array[String]): Unit = {
    val cal1 = new myMultiplication(3, 5)
    cal1.a = 8 // a=> both getter & setter , b=> only getter
    val prp = new Prop(2, 3)
    prp.mkl() // no getter and setter
    val mul = new myMultiplication(5, 6, "Shiro")
  }
}

class myMultiplication(var a: Int, val b: Int) {
  def multiply(): Int = {
    a * b
  }

  var teacherName: String = "yui  chan"

  def this(j: Int) { //could use variable "a" also instead of "j"
    this(j, 17)
  }

  def this(a: Int, b: Int, c: String) {
    this(a, b)
    this.teacherName = c

  }

  def this() {
    this(a, b, teacherName)
  }
}

class Prop(a: Int, b: Int) {
  def mkl(): Int = {
    println("addition result " + a + b)
    a + b
  }
}