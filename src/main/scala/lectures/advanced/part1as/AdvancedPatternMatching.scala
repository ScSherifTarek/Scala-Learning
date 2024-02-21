package lectures.advanced.part1as

object AdvancedPatternMatching extends App {

  object Even {
    def unapply(arg: Int): Boolean = arg % 2 == 0
  }

  object OneDigit {
    def unapply(arg: Int): Boolean = arg < 10
  }

  val n: Int = 5
  val matchPattern = n match {
    case Even() => s"${n} is an even number"
    case OneDigit() => s"${n} is a one digit number"
    case _ => s"${n} has no property"
  }
  println(matchPattern)
}
