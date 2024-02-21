package lectures.advanced.part1as

import scala.runtime.Nothing$

object AdvancedPatternMatching extends App {

  object Even {
    def unapply(arg: Int): Boolean = arg % 2 == 0
  }

  object OneDigit {
    def unapply(arg: Int): Boolean = arg < 10
  }

  val n: Int = 5
  val matchPattern = n match {
    case Even() => s"${n} is an even number"
    case OneDigit() => s"${n} is a one digit number"
    case _ => s"${n} has no property"
  }
  println(matchPattern)

  abstract class Wrapper[T] {
    def get: T
    def isEmpty: Boolean
  }

  object IntWrapper {
    def unapply(arg: Int): Wrapper[Int] =
      new Wrapper[Int] {
        def get: Int = arg * 10
        def isEmpty: Boolean = false
      }
  }

  println(n match {
    case IntWrapper(x) => s"${x} is my number"
  })
}
