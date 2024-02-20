package lectures.part4pm

object PatternMatching extends App {
  /*
      Exercise
      simple function uses PM
       takes an Expr => human readable form

       Sum(Number(2), Number(3)) => 2 + 3
       Sum(Number(2), Number(3), Number(4)) => 2 + 3 + 4
       Prod(Sum(Number(2), Number(1)), Number(3)) = (2 + 1) * 3
       Sum(Prod(Number(2), Number(1)), Number(3)) = 2 * 1 + 3
     */
  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  def show(expr: Expr): String =
    expr match {
      case Number(n) => s"${n}"
      case Sum(e1, e2) => s"${show(e1)} + ${show(e2)}"
      case Prod(Sum(e1, e2), Sum(e3, e4)) => s"(${show(e1)} + ${show(e2)}) * (${show(e3)} + ${show(e4)})"
      case Prod(Sum(e1, e2), e3) => s"(${show(e1)} + ${show(e2)}) * ${show(e3)}"
      case Prod(e1, Sum(e2, e3)) => s"${show(e1)} * (${show(e2)} + ${show(e3)})}"
      case Prod(e1, e2) => s"${show(e1)} * ${show(e2)}"
    }

  println(show(Prod(Sum(Number(1), Number(2)), Prod(Number(2),Number(3)))))
}
