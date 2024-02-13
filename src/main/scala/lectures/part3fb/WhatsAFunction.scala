package lectures.part3fb

object WhatsAFunction extends App {
  /*
    1.  a function which takes 2 strings and concatenates them
    2.  transform the MyPredicate and MyTransformer into function types
    3.  define a function which takes an int and returns another function which takes an int and returns an int
        - what's the type of this function
        - how to do it
   */
  val concatenate = new Function2[String, String, String] {
    override def apply(v1: String, v2: String): String = v1 + v2
  }
  println(concatenate("Hello ", "World"))

  val concatenate1 = (v1: String, v2: String) => v1 + v2
  println(concatenate1("Hello ", "World Again!!"))

  val f = new Function1[Int, Function1[Int, Int]] {
    override def apply(v1: Int): Int => Int = new Function1[Int, Int] {
      override def apply(v2: Int): Int = v1 + v2
    }
  }
  println(f(1)(2))
}
