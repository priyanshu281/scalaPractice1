object scalaBasics {
  val hi:List[Any] = List((3,4),(8,9))
  //objective is to get individual elements in variable like a = 3,b-4 etc

  var hi2= List((5,6),(2,3))
     val a2=hi2(1)._1
     //since this h2 approach is not possible here we have to conevert them to string first
     val  hi_redefined: Seq[Int] =hi.map(_.toString.toInt)


  /***
   * Scala Collection which one to use when
   *
   * ArrayBuffer --- to use when size is not known and random access is important, update or append takes constant time whereas prepend doesnt
   * ListBuffer --- to use when update is not required but append or prepend is important , add or remove is required and toList method returns constant time
   * MutableList --- https://stackoverflow.com/questions/5446744/difference-between-mutablelist-and-listbuffer


   ***/

}
