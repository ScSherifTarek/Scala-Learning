package problems

// 238. Product of Array Except Self
object ProductExceptSelf extends App {
  def productExceptSelf(nums: Array[Int]): Array[Int] = {
    val prefixes = nums.scan(1)(_ * _).dropRight(1)
    val postfixes = nums.scanRight(1)(_*_).drop(1)
    prefixes.zip(postfixes).map(_*_)
  }
  println(productExceptSelf(Array(1,2,3,4)).mkString(","))
  println(productExceptSelf(Array(-1,1,0,-3,3)).mkString(","))
}
