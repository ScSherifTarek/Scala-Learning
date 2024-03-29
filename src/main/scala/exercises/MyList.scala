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

  def foreach(f: A => Unit): Unit = {
    if (!isEmpty) {
      f(getHead)
      getTail.foreach(f)
    }
  }
  def sort(comparator: ((A, A) => Int)): MyList[A]
  def zipWith[B >: A](otherList: MyList[B], zipper: (A, B) => B): MyList[B]
  def fold[B >: A](start: B)(f: (A, B) => B): B
}

case object EmptyList extends MyList[Nothing] {
  def getHead: Nothing = throw new NoSuchElementException
  def getTail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[A >: Nothing](value: A): MyList[A] = new Cons[A](value, this)
  override def _toString: String = ""
  override def map[B](transform: (Nothing) => B): MyList[B] = this
  override def filter(predicate: (Nothing) => Boolean): MyList[Nothing] = this
  override def flatMap[B](transform: (Nothing) => MyList[B]): MyList[B] = this
  override def concat[B](rest: MyList[B]): MyList[B] = rest
  override def sort(comparator: (Nothing, Nothing) => Int): MyList[Nothing] = this

  override def zipWith[B >: Nothing](otherList: MyList[B], zipper: (Nothing, B) => B): MyList[B] = otherList

  override def fold[B >: Nothing](start: B)(f: (Nothing, B) => B): B = start
}

case class Cons[+A](val head: A, tail: MyList[A]) extends MyList[A] {
  def getHead: A = head
  def getTail: MyList[A] = tail
  def isEmpty: Boolean = false
  def add[B >: A](value: B): MyList[B] = new Cons[B](value, this)
  override def _toString: String =
    if(tail.isEmpty) s"$head"
    else s"$head ${tail._toString}"

  def map[B](transform: A => B): MyList[B] =
    new Cons(transform(head), tail.map(transform))

  def filter(predicate: (A) => Boolean): MyList[A] =
    if(predicate(head)) new Cons(head, tail.filter(predicate))
    else tail.filter(predicate)

  def concat[B >: A](rest: MyList[B]): MyList[B] =
    if (this.isEmpty) rest
    else if (this.getTail.isEmpty) rest.add(this.getHead)
    else this.getTail.concat(rest).add(this.getHead)
  def flatMap[B](transform: A => MyList[B]): MyList[B] =
    transform(head).concat(tail.flatMap(transform))

  override def sort(comparator: (A, A) => Int): MyList[A] = {
    if (tail.isEmpty) this
    else {
      val sortedTail = tail.sort(comparator)
      if(comparator(head, sortedTail.getHead) <= 0) {
        new Cons(head, sortedTail)
      } else {
        val rest = new Cons(head, sortedTail.getTail)
        new Cons(sortedTail.getHead, rest.sort(comparator))
      }
    }
  }

  override def zipWith[B >: A](otherList: MyList[B], zipper: (A, B) => B): MyList[B] = {
    new Cons(zipper(head, otherList.getHead), tail.zipWith(otherList.getTail, zipper))
  }

  override def fold[B >: A](start: B)(f: (A, B) => B): B = {
    tail.fold(f(head, start))(f)
  }
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

  val updatedListClone = updatedList.asInstanceOf[Cons[Int]].copy()
  println(updatedList)
  println(updatedListClone)
  println(updatedList == updatedListClone)
  val newUpdatedList = updatedListClone.add(5)
  println(newUpdatedList)
  println(updatedList)

  newUpdatedList.foreach(println)
  println(newUpdatedList.sort(_ - _))
  println(newUpdatedList.zipWith(newUpdatedList, _ * _))
  println(newUpdatedList.fold(0)(_ + _))

  val numbersList = MyList().add(1).add(2).add(3).add(4)
  val stringsList = MyList().add("ahmed").add("mohamed").add("mahmoud").add("hamada")
  val colorsList = MyList().add("red").add("black").add("yellow").add("blue")
  for {
    n <- numbersList 
    s <- stringsList
    c <- colorsList
  } yield println("" + n + s + "-" + c)

}


