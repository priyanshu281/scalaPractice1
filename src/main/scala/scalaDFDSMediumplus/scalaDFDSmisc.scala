package scalaDFDSMediumplus

object scalaDFDSmisc extends App {
//inner class and inner object example
  new outerClass().innerObject.meth()
  // new outerClass().innerClass2 // wont compile,innerClass2 api wont reflect in outerClass object
  val obj=new outerClass()
  var obj2=new obj.innerClass2()
  obj2.meth()
   //inner class inside object
  new outerObject.innerClass().meth()





}
class outerClass{
      object innerObject{
            def meth(): Unit ={
              println("innerObject of outer class")
            }
      }
      class innerClass2{
         def meth(): Unit ={
           println("inner Class inside outer class")
         }
      }
}
object outerObject{
  class innerClass{
    def meth(): Unit ={
      print("innerClass method of outer object")
    }
  }
}