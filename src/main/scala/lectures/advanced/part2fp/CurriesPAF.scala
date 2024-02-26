package lectures.advanced.part2fp

object CurriesPAF extends App {
  // EXERCISE
  val simpleAddFunction = (x: Int, y: Int) => x + y

  def simpleAddMethod(x: Int, y: Int) = x + y

  def curriedAddMethod(x: Int)(y: Int) = x + y

  // add7: Int => Int = y => 7 + y
  // as many different implementations of add7 using the above
  // be creative!
  val add7_1 = (y: Int) => simpleAddFunction(7, y)
  val add7_2 = (y: Int) => simpleAddMethod(7, y)
  val add7_3 = curriedAddMethod(7) _
  val add7_4 = (y: Int) => curriedAddMethod(7)(y)
  val add7_5 = simpleAddFunction.curried(7)
  val add7_6 = simpleAddMethod.curried(7)
  val add7_7 = curriedAddMethod(7)(_)
  val add7_8 = simpleAddMethod(7, _: Int)
  val add7_9 = simpleAddFunction(7, _: Int)

  // EXERCISES
  /*
    1.  Process a list of numbers and return their string representations with different formats
        Use the %4.2f, %8.6f and %14.12f with a curried formatter function.
   */
  def curriedFormatter(format: String)(n: Number): String = format.format(n)
  val formatter1 = curriedFormatter("%4.2f")
  val formatter2 = curriedFormatter("%8.6f")
  val formatter3 = curriedFormatter("%14.12f")

  val myList = List[Number](Math.PI, Math.E, 1342423423.52454325343545)
  println(myList.map(formatter1))
  println(myList.map(formatter2))
  println(myList.map(formatter3))

  /*
      2.  difference between
          - functions vs methods
          - parameters: by-name vs 0-lambda
     */
  def byName(n: => Int) = {
    print("In By Name -- ")
    n + 1
  }
  def byFunction(f: () => Int) = {
    print("In By Function -- ")
    f() + 1
  }
  def method: Int = {
    print("Method Applied -- ")
    42
  }
  def parenMethod(): Int = {
    print("Paren Method Applied -- ")
    42
  }
  /*
    calling byName and byFunction
    - int
    - method
    - parenMethod
    - lambda
    - PAF
   */

  def paf(x: Int)() = x + 42
  println("By Name")
  println(byName(42))
  println(byName(method))
  println(byName(parenMethod()))
//  println(byName(() => 42))
  val with10 = paf(10) _
//  println(byName(with10))

  println("By Function")
//  println(byFunction(42))
//  println(byFunction(method))
  println(byFunction(parenMethod))
  println(byFunction(() => 42))
  println(byFunction(with10))

}
