package problems

import scala.annotation.tailrec

// 2181. Merge Nodes in Between Zeros
class ListNode(_x: Int = 0, _next: ListNode = null) {
  var next: ListNode = _next
  var x: Int = _x
}
object MergeNodesInBetweenZeros extends App {
  def mergeNodes(head: ListNode): ListNode = {
    @tailrec
    def helper(cur: ListNode, sumSoFar: Int, acc: List[Int]): List[Int] = {
      if(cur == null) acc
      else if(cur.x == 0)
        helper(cur.next, 0, sumSoFar +: acc)
      else
        helper(cur.next, sumSoFar + cur.x, acc)
    }

    val tempList = helper(head.next, 0, List())
    val reducer = (nxtNode: ListNode, cur: Int) => new ListNode(cur, nxtNode)
    tempList.foldLeft[ListNode](null)(reducer)
  }
}
