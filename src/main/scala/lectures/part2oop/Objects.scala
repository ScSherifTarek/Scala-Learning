package lectures.part2oop

import scala.language.postfixOps

object Objects extends App {

// Companions::
  object Person {
    val N = 10
    def apply(name: String) = new Person(name)
  }
  class Person(val name: String) {
    def print: Unit = println(s"Hello I'm $name")
  }

  val p1 = new Person("Sherif")
  p1 print

  val p2 = new Person("Sherif")
  p2 print
}
