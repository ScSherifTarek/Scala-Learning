package lectures.basics.part3fb

object AnonymousFunctions extends App {
  /*
    1.  MyList: replace all FunctionX calls with lambdas
    2.  Rewrite the "special" adder as an anonymous function
   */
  val superAdder: Int => Int => Int = x => y => x + y
  val add3 = superAdder(3)
  println(add3(5))
  println(add3(10))
}
