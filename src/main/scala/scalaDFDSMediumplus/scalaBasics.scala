package scalaDFDSMediumplus

object scalaBasics {
  def main(args: Array[String]): Unit = {


    val k=Seq(("Now is "),("the time"),("to study"),("spark use cases"),("and solve problems")).flatMap(_.split(" "))
    /*** Here K will be list NOT A SEQ ***/

    val myl=List(Array(1,"ABC"),Array(2,"ASD"),Array(1,"ACDE"),Array(1,"CD"))

    // output map((1,ABCD),(2,"ASD"))
    myl.groupBy(x=> x(0)).mapValues(x=> x.map(y=> y(1)))
    //  u.flatMap(_.toLowerCase()).toSet
    myl.groupBy(x=> x(0)).mapValues(x => x.map(y=> y(1)).flatMap(_.toString().toUpperCase)).mapValues(_.toSet)
  }
}
