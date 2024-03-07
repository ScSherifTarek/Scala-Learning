package lectures.advanced.part4implicits

object TypeClasses extends App {
  trait Equal[T] {
    def apply(a: T, b: T): Boolean
  }

  case class Person(name: String, age: Int, email: String)
  implicit object NameEqual extends Equal[Person] {
    override def apply(a: Person, b: Person): Boolean = a.name == b.name
  }

  object FullEqual extends Equal[Person] {
    override def apply(a: Person, b: Person): Boolean = a.name == b.name && a.age == b.age && a.email == b.email
  }

  object Equal {
    def apply[T](a: T, b: T)(implicit equalizer: Equal[T]): Boolean = equalizer.apply(a, b)
  }

  implicit class EqualEnrichment[T](value: T) {
    def ===(anotherValue: T)(implicit equalizer: Equal[T]): Boolean = equalizer.apply(value, anotherValue)
    def !==(anotherValue: T)(implicit equalizer: Equal[T]): Boolean = !this.===(anotherValue)
  }

  val sherif = Person("Sherif", 25, "sherif@backend.com")
  val sherif2 = Person("Sherif", 32, "ahmed@backend.com")
  println(Equal(sherif, sherif2))
  println(Equal(sherif, sherif2)(FullEqual))
  println(sherif === sherif2)
  println(sherif !== sherif2)
}
