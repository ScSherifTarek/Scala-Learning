package lectures.part2oop

object Inheritance extends App {
  class Person(val name: String, val age: Int) {
    def sleep: Unit = println("zzzz..")
    protected def print: Unit = println(s"name: $name\nage: $age")
  }

  class Worker(name: String, age: Int, id: String) extends Person(name, age) {
    override def print = {
      super.print
      println(s"id: $id")
    }
  }

  val worker1: Worker = new Worker("Sherif", 25, "ID_SHERIF")
  worker1.sleep
  worker1.print
}
