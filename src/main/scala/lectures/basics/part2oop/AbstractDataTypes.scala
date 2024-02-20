package lectures.basics.part2oop

object AbstractDataTypes extends App {
  abstract class Animal(val name: String) {
    val age: Int
    def eat: Unit = println(s"$name is eating...")
    def hunt: Unit
  }

  class Eagle(val age: Int) extends Animal("Eagle") with CanFly("Eagle") {
    def hunt: Unit = println("Flying for a living")
  }

  val eagle = new Eagle(2)
  eagle.eat
  eagle.hunt


  trait CanFly(name: String) {
    def fly: Unit =  println(s"$name is flying")
  }
  trait CanDrink {
    def drink: Unit =  println("I'm drinking")
  }

  class Chicken(ageInMonths: Int) extends Animal("Chicken") with CanFly("Chicken") with CanDrink {
    val age = ageInMonths / 12
    def hunt: Unit = println("Cooked for a living")
  }

  val chicken = new Chicken(15)
  chicken.hunt
  chicken.eat
  chicken.fly
  chicken.drink

  val flyable: CanFly = eagle
  flyable.fly

  val x: CanDrink = chicken
  x.drink
}
