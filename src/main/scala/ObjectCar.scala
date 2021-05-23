import org.slf4j.{Logger, LoggerFactory}
import com.typesafe.config.{Config, ConfigFactory}

// Scala program of Design factory pattern

abstract class Car {
  def bookingPrice:Double
  //var stock=0
  // check this to understand field override of abstract class https://www.javatpoint.com/scala-field-overriding
  var stock:Int
  def brands:List[String]
  def book(noOfCars:Int)
}


object ObjectCar {
  val logger: Logger = LoggerFactory.getLogger(getClass.getSimpleName)

  private class standardCar extends Car{
    override def bookingPrice: Double = 200000
    override var stock=100
    override def brands: List[String] = List("Maruti","Mahindra","Toyota")
    //var stock1 =stock
    override def book(noOfCars: Int): Unit = stock=stock-noOfCars

  }
  private class luxeryCar extends Car{
    override def bookingPrice: Double = 2000000
    override var stock=10
    override def brands: List[String] = List("BMW","Porche","Audi")
    //var stock1 =stock
    override def book(noOfCars: Int): Unit = stock=stock-noOfCars
  }

  private class bmwCar extends Car{
    val config: Config = ConfigFactory.load("lightbend.conf")
    override def bookingPrice: Double = config.getDouble("mycar.price")

    override var stock: Int = config.getInt("mycar.stock")

    override def book(noOfCars: Int): Unit = stock=stock-noOfCars
    override def brands: List[String] = List(config.getString("mycar.manufacture"))
  }
  def main(args: Array[String]): Unit = {
    val sc=  new standardCar()
    println(sc.stock)
    sc.book(29)
    println(sc.stock)
    val lc= new luxeryCar()
    println(lc.stock)
    lc.book(2)
    println(lc.stock)
    logger.info("Have only two instances each of luxury and standard car")
    val bmwCar= new bmwCar()
    bmwCar.book(2)
    println("From config:"+bmwCar.stock)
    println("Our manufacturer:"+bmwCar.brands.head)
  }

}
