package lectures.advanced.part4implicits

object ImplicitsIntro extends App {
  def increment(counter: Int)(implicit amount: Int) = counter + amount
  implicit val hahaha: Int = 1

  println(increment(10))

  case class Person(name: String) {
    def greet: Unit = println(s"Hello I'm $name")
  }

  implicit def nameToPerson(name: String): Person = Person(name)

  "Sherif".greet

}
