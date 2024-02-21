package lectures.advanced.part2fp

import scala.util.Try

object PartialFunctions extends App {
  val f1: PartialFunction[Int, Int] = new PartialFunction[Int, Int] {
    override def apply(v1: Int): Int = v1 match
      case 1 => 42
      case 2 => 78
      case 3 => 1000

    override def isDefinedAt(x: Int): Boolean = Try(apply(x)).isSuccess
  }

  println(f1(1))
//  println(f1(5))
  println(f1.isDefinedAt(1))
  println(f1.isDefinedAt(5))


  scala.io.Source.stdin.getLines().map {
      case "Hi" => "Hello!!!"
      case "What's your name" => "Out of your business"
      case "OK" => "Okay!!"
  }.foreach(println)
}
