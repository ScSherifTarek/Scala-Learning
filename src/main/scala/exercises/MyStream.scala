package exercises

import scala.annotation.tailrec

abstract class MyStream[+A] {
  def isEmpty: Boolean

  def head: A

  def tail: MyStream[A]

  def #::[B >: A](element: B): MyStream[B] // prepend operator

  def ++[B >: A](anotherStream: => MyStream[B]): MyStream[B] // concatenate two streams

  def foreach(f: A => Unit): Unit

  def map[B](f: A => B): MyStream[B]

  def flatMap[B](f: A => MyStream[B]): MyStream[B]

  def filter(predicate: A => Boolean): MyStream[A]

  def take(n: Int): MyStream[A] // takes the first n elements out of this stream

  def takeAsList(n: Int): List[A]

  @tailrec
  final def toList[B >: A](acc: List[B] = List[B]()): List[B] = {
    if(this.isEmpty) acc
    else tail.toList(acc :+ head)
  }
}

object EmptyStream extends MyStream[Nothing] {
  def head: Nothing = throw new NoSuchElementException()
  def tail: MyStream[Nothing] = throw new NoSuchElementException()
  def isEmpty: Boolean = true

  def #::[A](element: A): MyStream[A] = new NonEmptyStream(element, this)

  def ++[A](anotherStream: => MyStream[A]): MyStream[A] = anotherStream

  def foreach(f: Nothing => Unit): Unit = ()

  def map[A](f: Nothing => A): MyStream[Nothing] = this

  def flatMap[A](f: Nothing => MyStream[A]): MyStream[Nothing] = this

  def filter(predicate: Nothing => Boolean): MyStream[Nothing] = this

  def take(n: Int): MyStream[Nothing] = this

  def takeAsList(n: Int): List[Nothing] = Nil
}

class NonEmptyStream[+A](h: A, tl: => MyStream[A]) extends MyStream[A] {
  def head: A = h
  def tail: MyStream[A] = tl
  def isEmpty: Boolean = false
  def #::[B >: A](element: B): MyStream[B] = new NonEmptyStream[B](element, this) // prepend operator

  def ++[B >: A](anotherStream: => MyStream[B]): MyStream[B] =
    new NonEmptyStream[B](head, tail ++ anotherStream)

  def foreach(f: A => Unit): Unit =
    f(head)
    tail.foreach(f)

  def map[B](f: A => B): MyStream[B] =
    new NonEmptyStream[B](f(head), tail.map(f))

  def flatMap[B](f: A => MyStream[B]): MyStream[B] =
    f(head) ++ tail.flatMap(f)

  def filter(predicate: A => Boolean): MyStream[A] =
    if (predicate(head)) new NonEmptyStream[A](head, tail.filter(predicate))
    else tail.filter(predicate)

  def take(n: Int): MyStream[A] = // takes the first n elements out of this stream
    if (n == 0) EmptyStream
    else new NonEmptyStream[A](head, tail.take(n - 1))

  def takeAsList(n: Int): List[A] =
    if (n == 0) List()
    else List(head) ++ tail.takeAsList(n - 1)
}

object MyStream {
  def from[A](start: A)(generator: A => A): MyStream[A] = {
    new NonEmptyStream[A](start, MyStream.from(generator(start))(generator))
  }
}

object Playground extends App {
  val naturals = MyStream.from(1)(_ + 1)
  println(naturals.head)
  println(naturals.tail.head)
  println(naturals.tail.tail.head)

  val startFrom0 = 0 #:: naturals // naturals.#::(0)
  println(startFrom0.head)

  startFrom0.take(10000).foreach(println)

  // map, flatMap
  println(startFrom0.map(_ * 2).take(100).toList())
  println((startFrom0 ++ startFrom0.map(_*2)).take(10).toList())
  println(startFrom0.flatMap(x => new NonEmptyStream(x, new NonEmptyStream(x + 1, EmptyStream))).take(10).toList())
  println(startFrom0.filter(_ < 10).take(10).take(20).toList())
}
