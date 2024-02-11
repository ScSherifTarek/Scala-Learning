package exercises

class MyList {
  var head: Node = null
  var tail: Node = null
  def getHead: Node = this.head
  def getTail: Node = this.tail
  def isEmpty: Boolean = this.head == null;
  def add(value: Int): Unit = {
    val node = new Node(value)
    if (null == tail) {
      head = node
      tail = node
      return
    }
    tail.next = node
    tail = node
  }

  override def toString: String = {
    if(null == head) {
      return ""
    }
    "[" + head.toString("") + "]"
  }
}

class Node(val value: Int, var next: Node = null) {
  def toString(prev: String = ""): String = {
    val cur = prev+value
    if(null == next) {
      return cur
    }
    next.toString(s"$cur -> ")
  }
}
object TestMyList extends App {
  val list = new MyList()
  list.add(5)
  list.add(6)
  println(list.head.value)
  println(list.tail.value)
  println(list.head.toString(""))
}


