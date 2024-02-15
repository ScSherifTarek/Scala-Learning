package exercises

import scala.annotation.tailrec

// 1528. Shuffle String
object ShuffleString extends App {
  def restoreString(s: String, indices: Array[Int]): String = {
    @tailrec
    def helper(i: Int, res: String): String = {
      if(i == s.length) res
      else {
        val j = indices.indexOf(i)
        helper(i + 1, res + s.charAt(j))
      }
    }
    helper(0, "")
  }

  println(restoreString("codeleet", Array(4,5,6,7,0,2,1,3)))
  println(restoreString("abc", Array(0,1,2)))
}
