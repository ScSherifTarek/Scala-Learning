package exercises


class Node(val value: Int)

abstract class MyList {
  def getHead: Node
  def getTail: Node
  def isEmpty: Boolean
  def add(value: Int): MyList
  def toString: String
}

class EmptyList extends MyList {
  def getHead: Node = null
  def getTail: Node = null
  def isEmpty: Boolean = true
  def add(value: Int): MyList = new List(value, this)
  override def toString: String = ""
}

class List(val value: Int, prev: MyList) extends MyList {
  val tail = new Node(value)

  def getHead: Node = {
    val prevHead: Node = prev.getHead
    if (prevHead == null) tail
    else prevHead
  }
  def getTail: Node = tail
  def isEmpty: Boolean = false
  def add(value: Int): MyList = new List(value, this)
  override def toString: String =
    if (prev.isEmpty) s"${tail.value}"
    else s"${prev.toString} ${tail.value}"
}

object MyList {
  def apply(): MyList =  new EmptyList
}

object TestMyList extends App {
  val list: MyList = MyList()

  val updatedList = list.add(5).add(6)
  println(updatedList.isEmpty)
  println(updatedList.getHead.value)
  println(updatedList.getTail.value)
  println(updatedList.toString)
}


