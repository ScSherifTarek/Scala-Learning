package exercises

abstract class MyList {
  def getHead: Int
  def getTail: MyList
  def isEmpty: Boolean
  def add(value: Int): MyList
  def toString: String
}

object EmptyList extends MyList {
  def getHead: Int = throw new NoSuchElementException
  def getTail: MyList = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add(value: Int): MyList = new List(value, this)
  override def toString: String = ""
}

class List(val head: Int, tail: MyList) extends MyList {
  def getHead: Int = head
  def getTail: MyList = tail
  def isEmpty: Boolean = false
  def add(value: Int): MyList = new List(value, this)
  override def toString: String =
    if(tail.isEmpty) s"$head"
    else s"$head ${tail.toString}"
}

object MyList {
  def apply(): MyList =  EmptyList
}

object TestMyList extends App {
  val list: MyList = MyList()

  val updatedList = list.add(5).add(6)
  println(updatedList.isEmpty)
  println(updatedList.getHead)
  println(updatedList.getTail)
  println(updatedList.toString)
}


