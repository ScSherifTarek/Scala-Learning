package lectures.advanced.part4implicits

object OrganizingImplicits extends App {
  case class Person(name: String, age: Int)
  val persons = List(
    Person("Sherif", 10),
    Person("Ahmed", 50),
    Person("Hamada", 23)
  )

  implicit val personOrdering: Ordering[Person] = Ordering.fromLessThan(_.name < _.name)

  println(persons.sorted)
}
