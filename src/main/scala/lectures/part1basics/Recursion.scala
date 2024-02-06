package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {
  def concatenateNTimes(word: String, n: Int): String = {
    @tailrec
    def concatHelper(sentence: String, remainingTimes: Int): String = {
      if (remainingTimes <= 0) sentence
      else concatHelper(sentence + word, remainingTimes - 1)
    }

    concatHelper("", n)
  }
  println(concatenateNTimes("Hello", 3))


  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeUntil(i: Int): Boolean = {
      if (i <= 1) true
      else if (n % i == 0) false
      else isPrimeUntil(i - 1)
    }

    isPrimeUntil(math.sqrt(n).toInt)
  }
  println(isPrime(37))
  println(isPrime(2003))
  println(isPrime(37 * 17))

  def fib(n: Int): Int = {
    @tailrec
    def fibHelper(i: Int, prev1: Int, prev2: Int): Int = {
      val cur = prev1 + prev2
      if(i >= n) cur
      else fibHelper(i+1, prev2, cur)
    }

    if(n <= 2) 1
    else fibHelper(3, 1, 1)
  }
  println(fib(8))
}
