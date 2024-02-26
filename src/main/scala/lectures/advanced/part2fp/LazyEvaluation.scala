package lectures.advanced.part2fp

object LazyEvaluation extends App {
  def byNameMethod(n: => Int): Int = {
    // CALL BY NEED
    lazy val t = n // only evaluated once
    t + t + t + 1
  }

  def byNameMethodEquivalent(n: => Int): Int = {
    val t = n // only evaluated once
    t + t + t + 1
  }

  def retrieveMagicValue = {
    // side effect or a long computation
    println("waiting")
    Thread.sleep(1000)
    42
  }

  println(byNameMethod(retrieveMagicValue))
  println(byNameMethodEquivalent(retrieveMagicValue))

  /*
      Exercise: implement a lazily evaluated, singly linked STREAM of elements.

      naturals = MyStream.from(1)(x => x + 1) = stream of natural numbers (potentially infinite!)
      naturals.take(100).foreach(println) // lazily evaluated stream of the first 100 naturals (finite stream)
      naturals.foreach(println) // will crash - infinite!
      naturals.map(_ * 2) // stream of all even numbers (potentially infinite)
     */
}
