package lectures.basics.part1basics

object Functions extends App {
  def greeting(name: String, age: Int): Unit = println(f"Hi, my name is $name and I'm $age years old")
  greeting("Sherif", 25)

  def factorial(n: Int): Int =
    if(n<=1) 1
    else n * factorial(n-1)
  println(factorial(5))

  def fibonacci(n: Int): Int =
    if(n <= 2) 1
    else fibonacci(n-1) + fibonacci(n-2)
  println(fibonacci(8))

  def isPrime(n: Int): Boolean = {
    def check(i: Int): Boolean = {
      if (i == 1) true
      else if (n % i == 0) false
      else check(i - 1)
    }

    val sqrtN = math.sqrt(n).ceil.toInt
    check(sqrtN)
  }
  println(isPrime(11))
  println(isPrime(23))
  println(isPrime(27))
  println(isPrime(37))
  println(isPrime(2003))
  println(isPrime(37 * 17))
}
