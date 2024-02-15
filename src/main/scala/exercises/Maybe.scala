package exercises

abstract class Maybe[+A] {
  def map[B](transform: (A) => B): Maybe[B]
  def filter(predicate: (A) => Boolean): Maybe[A]
  def flatMap[B](transform: (A) => Maybe[B]): Maybe[B]
}

case object MaybeNot extends Maybe[Nothing] {
  def map[B](transform: (Nothing) => B): Maybe[B] = MaybeNot
  def filter(predicate: (Nothing) => Boolean): Maybe[Nothing] = MaybeNot
  def flatMap[B](transform: (Nothing) => Maybe[B]): Maybe[B] = MaybeNot
}

case class Just[+A](value: A) extends Maybe[A] {
  def map[B](transform: (A) => B): Maybe[B] = Just(transform(value))

  def filter(predicate: (A) => Boolean): Maybe[A] =
    if(predicate(value)) this
    else MaybeNot

  def flatMap[B](transform: (A) => Maybe[B]): Maybe[B] = transform(value)
}

object MaybeTest extends App {
  val just3 = Just(3)
  println(just3)
  println(just3.map(_*2))
  println(just3.flatMap(x => Just(x%2==0)))
  println(just3.filter(_%2==0))

}