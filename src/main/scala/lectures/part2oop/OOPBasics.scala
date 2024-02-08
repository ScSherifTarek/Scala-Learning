package lectures.part2oop

object OOPBasics extends App {
  val p = new Person()
  p.greet()

  val author1 = new Author("Sherif", "Tarek", 1999)
  val author2 = new Author("Ahmed", "Tarek", 2005)
  println(author1.getFullName())

  val novel1 = new Novel("Awesome Novel", 2022, author1)
  novel1.printDetails()
  println(novel1.isWrittenBy(author1))
  println(novel1.isWrittenBy(author2))

  val novel2 = novel1.copy(2024)
  novel1.printDetails()
  novel2.printDetails()
  println(novel2.isWrittenBy(author1))
  println(novel2.isWrittenBy(author2))

  val counter1 = new Counter(0)
  counter1.increment()
  counter1.increment()
  println(counter1.getCurrent)
  counter1.decrement()
  println(counter1.getCurrent)

  println(counter1.increment(10).increment().increment().decrement().getCurrent)

}

class Person(name: String, val age: Int = 0) {
  val x = 2
  println("Hello from class main constructor")

  def greet(name: String) = println(s"$this.name says: Hi, $name")
  def greet() = println(s"Hi, I'm $name")

  def this(age: Int) = this("Sherif Tarek", age)
  def this() = this("Sherif Tarek", 25)
}

class Author(firstName: String, lastName: String, yearOfBirth: Int) {
  def getFullName(): String = s"$firstName $lastName"
  def getAgeIn(year: Int): Int = year - yearOfBirth
}

class Novel(name: String, releaseYear: Int, author: Author) {
  def getAuthorAge: Int = author.getAgeIn(releaseYear)
  def isWrittenBy(someAuthor: Author): Boolean = author == someAuthor
  def copy(newReleaseYear: Int): Novel = new Novel(this.name, newReleaseYear, this.author)

  def printDetails(): Unit = println(s"$name[$releaseYear] written by ${author.getFullName()}")
}

class Counter(current: Int) {
  def getCurrent: Int = current
  def increment(value: Int = 1): Counter = new Counter(current + value)
  def decrement(value: Int = 1): Counter = new Counter(current - value)
}