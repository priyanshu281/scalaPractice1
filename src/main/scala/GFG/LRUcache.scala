package GFG
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
class LRUcache(capacity:Int) {
  //using listbuffer as we have to append and prepend continuously.
  val lb: ListBuffer[Int] = ListBuffer[Int]()
  val hs: mutable.Set[Int] = mutable.HashSet[Int]()
  val  CACHE_SIZE: Int = capacity

  def refer(page: Int): Unit = {
    if (!hs.contains(page)) {
      if (lb.size == CACHE_SIZE) {
        var lst =lb.remove(0) //removing last element
        hs.remove(lst)
      }
    } else {

      lb -= page
    }

      lb += page
      hs.add(page)

  }
  def getSize():Int= lb.size
  def display(): Unit = lb.foreach(x => println(x))
}
  object lruCacheDemo {
    def main(args: Array[String]): Unit = {
      val lru = new LRUcache(4)
      lru.refer(1)
      lru.refer(2)
      lru.refer(3)
      lru.refer(4)
      lru.refer(5)
      lru.refer(2)
      lru.refer(2)
      lru.refer(1)
      lru.display()
      println("Current Size of cach:"+lru.getSize())
    }

  }
