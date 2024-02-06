package lectures.part1basics

object Expressions extends App {
  val x = 8
  println(x >>> 1)
  println(x >> 1)


  val y = -8
  println(y >> 1)
  println(y >>> 1)
  println(y << 1)

  println(f"8: ${8} --> binary ${8.toBinaryString}")
  println(f"1: ${1} --> binary ${1.toBinaryString}")
  println(f"0: ${0} --> binary ${0.toBinaryString}")
  println(f"-1: ${-1} --> binary ${-1.toBinaryString}")
  println(f"8 >> 1: ${8 >> 1} --> binary ${(8 >> 1).toBinaryString}")
  println(f"8 >>> 1: ${8 >>> 1} --> binary ${(8 >>> 1).toBinaryString}")
  println(f"0 >> 1: ${0 >> 1} --> binary ${(0 >> 1).toBinaryString}")
  println(f"-1 >> 1: ${-1 >> 1} --> binary ${(-1 >> 1).toBinaryString}")  // -1 ->          11111111111111111111111111111111
  println(f"-1 >>> 1: ${-1 >>> 1} --> binary ${(-1 >>> 1).toBinaryString}") // 2147483647 -> 01111111111111111111111111111111
  println(f"-1 >>> 3: ${-1 >>> 3} --> binary ${(-1 >>> 3).toBinaryString}") // 536870911 ->  00011111111111111111111111111111
  println(f"-1 >>> 2 >>> 1: ${-1 >>> 2 >>> 1} --> binary ${(-1 >>> 2 >>> 1).toBinaryString}") // 536870911 -> 00011111111111111111111111111111

  println(Int.MaxValue == (-1 >> 1)) // false
  println(Int.MaxValue == (-1 >>> 1)) // true
  println(Int.MaxValue == -1) // false
  println((Int.MaxValue & -1) == -1) // false
  println((Int.MaxValue & -1) == Int.MaxValue) // true
  println((Int.MaxValue | -1) == Int.MaxValue) // false
  println((Int.MaxValue | -1) == -1) // true


  var unit: Unit = ()
  println(unit)

  var i = 0
  unit = while(i < 3) {
    println(i)
    i += 1
  }


  val aVal = if(x < 10) 10 else "20" // Type Any
  println(aVal)
  println(aVal.toString == "10") // true
  println(aVal == "10") // false
  println(aVal == 10) // true
  // println(aVal.toString == 10) // Not accepted by the compiler, can't compare String and Int

  var codeBlockResult = {
    val z = 2
    x + z
  }
  println(codeBlockResult)

  codeBlockResult = {
    val z = 2
    {
      val k = 10
      x + z + k
    }
  }
  println(codeBlockResult)

}
