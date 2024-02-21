package lectures.advanced.part1as

object AdvancedPatternMatching extends App {

  object Even {
    def unapply(arg: Int): Option[Int] = if arg % 2 == 0 then Some(arg) else None
  }

  object OneDigit {
    def unapply(arg: Int): Option[Int] = if arg < 10 then Some(arg) else None
  }

  val n: Int = 5
  val matchPattern = n match {
    case Even(n) => s"${n} is an even number"
    case OneDigit(n) => s"${n} is a one digit number"
    case n => s"${n} has no property"
  }
  println(matchPattern)
}
