package lectures.part1basics

object CBNvsCBV extends App {
  def callByValue(x: Long): Unit = {
    println("Call by value: " + x)
    println("Call by value: " + x)
  }

  def callByName(x: => Long): Unit = {
    println("Call by name: " + x)
    println("Call by name: " + x)
  }

  callByValue(System.nanoTime())
  callByName(System.nanoTime())

  def infinite(): Int = 1 + infinite()
  def printFirst(first: Int, second: => Int): Unit = println(first)
  printFirst(100, infinite())
}
