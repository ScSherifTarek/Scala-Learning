package exercises

import scala.annotation.tailrec

trait MySet[A] extends (A => Boolean) {
  def contains(elem: A): Boolean
  def +(elem: A): MySet[A]
  def ++(another: MySet[A]): MySet[A]
  def map[B](f: A => B): MySet[B]
  def flatMap[B](f: A => MySet[B]): MySet[B]
  def filter(predicate: A => Boolean): MySet[A]
  def foreach(f: A => Unit): Unit
  def isEmpty: Boolean = true
  def getPrintFormat: String
  def -(v: A): MySet[A] = this.filter(_ != v)
  def &&(another: MySet[A]): MySet[A] = this.filter(another.contains)
  def --(another: MySet[A]): MySet[A] = this.filter(!another.contains(_))
  override def apply(v1: A): Boolean = this.contains(v1)
  override def toString(): String = "(" + this.getPrintFormat + ")"
}

class EmptySet[A] extends MySet[A] {
  override def contains(elem: A): Boolean = false
  override def +(elem: A): MySet[A] = new NonEmptySet[A](elem, this)
  override def ++(another: MySet[A]): MySet[A] = another
  override def filter(predicate: A => Boolean): MySet[A] = this
  override def map[B](f: A => B): MySet[B] = new EmptySet[B]
  override def flatMap[B](f: A => MySet[B]): MySet[B] = new EmptySet[B]
  override def foreach(f: A => Unit): Unit = ()
  override def getPrintFormat: String = ""
}

case class NonEmptySet[A](val head: A, val rest: MySet[A]) extends MySet[A] {

  override def contains(elem: A): Boolean = (head == elem) || rest.contains(elem)

  override def +(elem: A): MySet[A] =
    if(this.contains(elem)) this
    else new NonEmptySet[A](elem, this)

  override def ++(another: MySet[A]): MySet[A] =
    rest ++ another + head

  override def filter(predicate: A => Boolean): MySet[A] =
    if predicate(head) then rest.filter(predicate) + head else rest.filter(predicate)

  override def map[B](f: A => B): MySet[B] = rest.map(f) + f(head)

  override def flatMap[B](f: A => MySet[B]): MySet[B] = rest.flatMap(f) ++ f(head)
  override def foreach(f: A => Unit): Unit =
    f(head)
    rest.foreach(f)

  override def isEmpty: Boolean = false
  override def getPrintFormat: String =
    if(rest.isEmpty) s"${head}"
    else s"${head} ${rest.getPrintFormat}"
}

object MySet {
  def apply[A](elements: A*): MySet[A] =
    @tailrec
    def buildSet(valSeq: Seq[A], acc: MySet[A]): MySet[A] =
      if(valSeq.isEmpty) acc
      else buildSet(valSeq.tail, acc + valSeq.head)
    buildSet(elements.toSeq, new EmptySet[A])
}

object TestMySet extends App {
  val s1 = MySet(5) ++ (MySet(6) + 7) ++ (MySet(8) + 6) + 10 + 5 + 9 + 11
  println(s1.toString())
  println(s1.map(_*10))
  println(s1.filter(_%2 == 0))
  println(s1.flatMap(x => (MySet(x*10) + x*100)))
  s1.foreach(println)

  val s2 = MySet(5, 6, 7, 8, 6, 10, 5, 14, 18, 20)
  println(s2.toString())
  println(s2.map(_ * 10))
  println(s2.filter(_ % 2 == 0))
  println(s2.flatMap(x => MySet(x * 10, x * 100)))
  s2.foreach(println)

  println(s1)
  println(s2)
  println(s1 - 5)
  println(s1 && s2)
  println(s1 -- s2)
}

