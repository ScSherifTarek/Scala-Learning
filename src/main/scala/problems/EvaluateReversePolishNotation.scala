package problems

import scala.annotation.tailrec
import scala.language.postfixOps

// 150. Evaluate Reverse Polish Notation
object EvaluateReversePolishNotation extends App {
  def evalRPN(tokens: Array[String]): Int = {
    def ++(stack: Vector[Int]): Vector[Int] = {
      val res = stack.tail.head + stack.head
      res +: stack.tail.tail
    }

    def --(stack: Vector[Int]): Vector[Int] = {
      val res = stack.tail.head - stack.head
      res +: stack.tail.tail
    }

    def **(stack: Vector[Int]): Vector[Int] = {
      val res = stack.tail.head * stack.head
      res +: stack.tail.tail
    }

    def /(stack: Vector[Int]): Vector[Int] = {
      val res = stack.tail.head / stack.head
      res +: stack.tail.tail
    }

    @tailrec
    def helper(i: Int, stack: Vector[Int]): Int = {
      if(i==tokens.length) {
        stack.head
      } else {
        val updatedStack: Vector[Int] =
          if (tokens(i) == "+") ++(stack)
          else if (tokens(i) == "-") --(stack)
          else if (tokens(i) == "*") **(stack)
          else if (tokens(i) == "/") /(stack)
          else tokens(i).toInt +: stack
        helper(i + 1, updatedStack)
      }
    }

    helper(0, Vector[Int]())
  }

  println(evalRPN(Array("2","1","+","3","*")))
  println(evalRPN(Array("4","13","5","/","+")))
  println(evalRPN(Array("10","6","9","3","+","-11","*","/","*","17","+","5","+")))
}
