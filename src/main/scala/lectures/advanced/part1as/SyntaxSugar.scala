package lectures.advanced.part1as

object SyntaxSugar extends App {
  trait MutableContainer {
    var value: Int = 0
    def member: Int = value
    def member_=(v: Int): MutableContainer = {
      value = v
      this
    }

    def another_=(v: Int): Unit = value = v
  }

  val x = new MutableContainer {}
  println(x)
  println(x.member)
  println(x.member = 100)
  println(x.member)
//  x.another = 200 // not working, compiler can't see it
  println(x.member)

  trait MutableContainerV2 {
    var value: Int = 0

    def member: Int = value

    def member_=(v: Int): MutableContainerV2 = {
      value = v
      this
    }

    def another: Int = value

    def another_=(v: Int): Unit = value = v
  }

  val y = new MutableContainerV2 {}
  println(y)
  println(y.member)
  println(y.member = 100)
  println(y.member)
  y.another = 200 // works
  println(y.member)
  println(y.another)
}
