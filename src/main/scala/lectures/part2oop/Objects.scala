package lectures.part2oop

import scala.language.postfixOps

object Objects extends App {

// Companions::
  object Person {
    val N = 10
    def apply(name: String) = new Person(name)
    def whisper(person: Person): Unit = person privatePrint
  }
  class Person(val name: String) {
    def print: Unit = println(s"Hello I'm $name")
    private def privatePrint: Unit = println(s"Psst, Hi, I'm $name")
  }

  val p1 = new Person("Sherif")
  p1 print

  val p2 = new Person("Ahmed")
  p2 print

  Person.whisper(p2)

  def print(person: Person): Unit = person.print

  print(p2)
}
