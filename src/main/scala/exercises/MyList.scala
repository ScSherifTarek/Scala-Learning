package exercises

abstract class MyList[+A] {
  def getHead: A
  def getTail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](value: B): MyList[B]
  override def toString: String = "["+this._toString+"]"
  def _toString: String
  def map[B](transform: (A) => B): MyList[B]
  def filter(predicate: (A) => Boolean): MyList[A]
  def flatMap[B](transform: (A) => MyList[B]): MyList[B]
  def concat[B >: A](rest: MyList[B]): MyList[B]
}

case object EmptyList extends MyList[Nothing] {
  def getHead: Nothing = throw new NoSuchElementException
  def getTail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[A >: Nothing](value: A): MyList[A] = new List[A](value, this)
  override def _toString: String = ""
  override def map[B](transform: (Nothing) => B): MyList[B] = this
  override def filter(predicate: (Nothing) => Boolean): MyList[Nothing] = this
  override def flatMap[B](transform: (Nothing) => MyList[B]): MyList[B] = this
  override def concat[B](rest: MyList[B]): MyList[B] = rest
}

case class List[+A](val head: A, tail: MyList[A]) extends MyList[A] {
  def getHead: A = head
  def getTail: MyList[A] = tail
  def isEmpty: Boolean = false
  def add[B >: A](value: B): MyList[B] = new List[B](value, this)
  override def _toString: String =
    if(tail.isEmpty) s"$head"
    else s"$head ${tail._toString}"

  def map[B](transform: A => B): MyList[B] =
    new List(transform(head), tail.map(transform))

  def filter(predicate: (A) => Boolean): MyList[A] =
    if(predicate(head)) new List(head, tail.filter(predicate))
    else tail.filter(predicate)

  def concat[B >: A](rest: MyList[B]): MyList[B] =
    if (this.isEmpty) rest
    else if (this.getTail.isEmpty) rest.add(this.getHead)
    else this.getTail.concat(rest).add(this.getHead)
  def flatMap[B](transform: A => MyList[B]): MyList[B] =
    transform(head).concat(tail.flatMap(transform))
}

object MyList {
  def apply[A](): MyList[A] =  EmptyList
}

object TestMyList extends App {
  val list: MyList[Int] = MyList[Int]()
  println(list.isEmpty)

  val updatedList = list.add(5).add(6).add(105)
  println(updatedList.isEmpty)
  println(updatedList.getHead)
  println(updatedList.getTail)
  println(updatedList.toString)

  val lessThan10: Int => Boolean = _ < 10
  val multiplyBy10: Int => Int = _ * 10
  val mapToOnePositiveAndOneNegative: Int => MyList[Int] = x => MyList().add(-x).add(x)

  println(updatedList.filter(lessThan10))
  println(updatedList.filter(lessThan10).map(multiplyBy10))
  println(updatedList.filter(lessThan10).map(multiplyBy10).flatMap(mapToOnePositiveAndOneNegative))

  val updatedListClone = updatedList.asInstanceOf[List[Any]].copy()
  println(updatedList)
  println(updatedListClone)
  println(updatedList == updatedListClone)
  val newUpdatedList = updatedListClone.add(5)
  println(newUpdatedList)
  println(updatedList)

}


