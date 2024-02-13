package lectures.part2oop

object Exceptions extends App {
  /*
      1.  Crash your program with an OutOfMemoryError
      2.  Crash with SOError
      3.  PocketCalculator
          - add(x,y)
          - subtract(x,y)
          - multiply(x,y)
          - divide(x,y)

          Throw
            - OverflowException if add(x,y) exceeds Int.MAX_VALUE
            - UnderflowException if subtract(x,y) exceeds Int.MIN_VALUE
            - MathCalculationException for division by 0
     */
//  Array.fill(Int.MaxValue){Long.MaxValue}

//  def infiniteRecursion(): Int = 1 + infiniteRecursion()
//  infiniteRecursion()

  class OverflowException extends RuntimeException
  class UnderflowException extends RuntimeException
  class MathCalculationException extends RuntimeException("Division by 0")
  class PocketCalculator {
    def add(x: Int, y: Int): Int = {
      if(Int.MaxValue - x < y) throw new OverflowException
      else x + y
    }
    def substract(x: Int, y: Int): Int = {
      if (Int.MinValue + y > x) throw new UnderflowException
      else x - y
    }
    def multiply(x: Int, y: Int): Int = x * y
    def divide(x: Int, y: Int): Float =
      if(y == 0) throw new MathCalculationException
      else x.toFloat / y
  }

  val calculator = new PocketCalculator
//  println(calculator.add(Int.MaxValue, Int.MaxValue))
//  println(calculator.substract(Int.MinValue, Int.MaxValue))
  println(calculator.multiply(10, 10))
  println(calculator.divide(10, 0))
}
