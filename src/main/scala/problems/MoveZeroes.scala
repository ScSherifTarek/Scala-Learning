package problems

object MoveZeroes extends App {
  def moveZeroes(nums: Array[Int]): Unit = {
    val nonZeroes = nums.filter(_ != 0)
    val res = nonZeroes ++ Array.fill(nums.length - nonZeroes.length)(0)
    res.copyToArray(nums)
  }
}
