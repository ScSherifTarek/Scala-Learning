package lectures.basics.part3fb

object MapFlatmapFilterFor extends App {
  val numbers = List(1, 2, 3)
  val chars = List('a', 'b', 'c')
  val colors = List("red", "black", "white")
  val combinations = numbers.filter(_%2==0).flatMap(number => chars.flatMap(char => colors.map(color => s"$number$char-$color")))
  println(combinations)

  numbers.foreach(println)

  val forCombinations = for {
    number <- numbers if number % 2 == 0
    char <- chars
    color <- colors
  } yield s"$number$char-$color"
  println(forCombinations)

  println(numbers.map {x => x*2}.map {_+1})
}
