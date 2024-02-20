package lectures.basics.part1basics

object StringOps extends App {
  val str: String = "Hello, I am learning Scala"
  println(str.replace(' ', '-'))
  println(str.replaceAll("[a-zA-Z]", "-"))

  val x = "2"
  val aStr: String = x.+:('a') :+ 'z'
  val aVector: Vector[Matchable] = ("a" +: x :+ "z").toVector
  println('a' +: x :+ 'z')
  println('a' +: x :+ "z")
  val name = "Sherif"
  val age = 25
  val sGreeting = s"This is $name, \n and I'm $age years old"
  println(sGreeting)

  val fGreeting = f"This is $name, \n and I'm $age years old"
  println(fGreeting)

  val rawGreeting: String = raw"This is $name, \n and I'm $age years old"
  println(rawGreeting)


}
