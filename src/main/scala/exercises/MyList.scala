package exercises

abstract class MyList[+A] {
  def getHead: A
  def getTail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](value: B): MyList[B]
  def toString: String
  def map[B](transformer: MyTransformer[A, B]): MyList[B]
  def filter(predicate: MyPredicate[A]): MyList[A]
  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]
  def concat[B >: A](rest: MyList[B]): MyList[B]
}

object EmptyList extends MyList[Nothing] {
  def getHead: Nothing = throw new NoSuchElementException
  def getTail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[A >: Nothing](value: A): MyList[A] = new List[A](value, this)
  override def toString: String = ""
  override def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = this
  override def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = this
  override def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = this
  override def concat[B](rest: MyList[B]): MyList[B] = rest
}

class List[+A](val head: A, tail: MyList[A]) extends MyList[A] {
  def getHead: A = head
  def getTail: MyList[A] = tail
  def isEmpty: Boolean = false
  def add[B >: A](value: B): MyList[B] = new List[B](value, this)
  override def toString: String =
    if(tail.isEmpty) s"$head"
    else s"$head ${tail.toString}"

  def map[B](transformer: MyTransformer[A, B]): MyList[B] =
    new List(transformer.transform(head), tail.map(transformer))

  def filter(predicate: MyPredicate[A]): MyList[A] =
    if(predicate.test(head)) new List(head, tail.filter(predicate))
    else tail.filter(predicate)

  def concat[B >: A](rest: MyList[B]): MyList[B] =
    if (this.isEmpty) rest
    else if (this.getTail.isEmpty) rest.add(this.getHead)
    else this.getTail.concat(rest).add(this.getHead)
  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] =
    transformer.transform(head).concat(tail.flatMap(transformer))
}

object MyList {
  def apply[A](): MyList[A] =  EmptyList
}

trait MyPredicate[-T] {
  def test(o: T): Boolean
}

trait MyTransformer[-A, B] {
  def transform(o: A): B
}
object TestMyList extends App {
  val list: MyList[Int] = MyList[Int]()
  println(list.isEmpty)

  val updatedList = list.add(5).add(6).add(105)
  println(updatedList.isEmpty)
  println(updatedList.getHead)
  println(updatedList.getTail)
  println(updatedList.toString)

  val lessThan10 = new MyPredicate[Int] {
    def test(o: Int): Boolean = o < 10
  }
  val multiplyBy10 = new MyTransformer[Int, Int] {
    def transform(o: Int): Int = o * 10
  }
  val mapToOnePositiveAndOneNegative = new MyTransformer[Int, MyList[Int]] {
    def transform(o: Int): MyList[Int] = MyList().add(-o).add(o)
  }

  println(updatedList.filter(lessThan10))
  println(updatedList.filter(lessThan10).map(multiplyBy10))
  println(updatedList.filter(lessThan10).map(multiplyBy10).flatMap(mapToOnePositiveAndOneNegative))
}


