package lectures.basics.part2oop

import scala.annotation.targetName
import scala.language.postfixOps

object MethodNotations extends App {
  class Person(val name: String, favoriteMovie: String, val age: Int = 0) {
    def likes(movie: String): Boolean = favoriteMovie == movie
    def +(person: Person): String = s"${this.name} hangs out with ${person.name}"
    def +(nickname: String): Person = new Person(s"$name ($nickname)", favoriteMovie, age)
    def unary_! : String = s"${this.name} says WOW!!!"
    def unary_+ : Person = new Person(name, favoriteMovie, age+1)
    def isHappy: Boolean = true
    def learns(name: String): String = s"${this.name} learns $name"
    def learnsScala: String = learns("Scala")
    def apply(): String = s"${this.name} says: I'm in"
    def apply(n: Int): String = s"${this.name} watched ${this.favoriteMovie} $n times"
  }

  // infix
  val sherif = new Person("Sherif", "First Movie")
  println(sherif likes "Second Movie")

  val hamada = new Person("Hamada", "Second Movie")
  println(sherif + hamada)

  // unary_ prefix only works with - + ~ !
  println(!sherif)
  println((+sherif) likes "Second Movie")
  println((+sherif) likes "First Movie")
  println((+sherif).age)

  // postfix
  println(sherif isHappy)

  // apply
  println(sherif())
  println(sherif.apply())
  println(sherif.apply(2))

  println(sherif learns "Scala")
  println(sherif learnsScala)


  val withNickName = sherif + "Awesome Man"
  println(withNickName())
}
