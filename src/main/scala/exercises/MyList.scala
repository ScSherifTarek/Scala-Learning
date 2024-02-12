package exercises

abstract class MyList[+A] {
  def getHead: A
  def getTail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](value: B): MyList[B]
  def toString: String
}

object EmptyList extends MyList[Nothing] {
  def getHead: Nothing = throw new NoSuchElementException
  def getTail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[A >: Nothing](value: A): MyList[A] = new List[A](value, this)
  override def toString: String = ""
}

class List[+A](val head: A, tail: MyList[A]) extends MyList[A] {
  def getHead: A = head
  def getTail: MyList[A] = tail
  def isEmpty: Boolean = false
  def add[B >: A](value: B): MyList[B] = new List[B](value, this)
  override def toString: String =
    if(tail.isEmpty) s"$head"
    else s"$head ${tail.toString}"
}

object MyList {
  def apply[A](): MyList[A] =  EmptyList
}

object TestMyList extends App {
  val list: MyList[Any] = MyList[Int]()
  println(list.isEmpty)

  val updatedList = list.add(5).add(6).add("Hamada").add(4.0)
  println(updatedList.isEmpty)
  println(updatedList.getHead)
  println(updatedList.getTail)
  println(updatedList.toString)
}


