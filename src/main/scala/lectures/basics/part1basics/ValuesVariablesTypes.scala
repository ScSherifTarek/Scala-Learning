package lectures.basics.part1basics

object ValuesVariablesTypes extends App {
  val x: Int = 5
  println(x)

  val aString: String = "Hello World!"
  val aBoolean: Boolean = false
  val aChar: Char = 'a'
  val anInt: Int = x
  val maxShort: Short = math.pow(2, 15).shortValue // 32767 -- Should be 2^15 - 1 --- it overflows! :)
  val minShort: Short = ((-1 * maxShort) - 1).toShort // -32768
  println(minShort)
  println(maxShort)
  val maxLong: Long = math.pow(2, 63).longValue // 9223372036854775807 (9.223372036854776e18) -- Should be 2^63 - 1
  val minLong: Long = (-1 * maxLong) - 1
  println(minLong)
  println(maxLong)

  val aFloat: Float = 2.0f
  val aDouble: Double = 3.14
  println(aFloat)
  println(aDouble)

  var aVariable: Int = x
  aVariable = 1

  println(math.pow(2, 15))
}
