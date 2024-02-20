package lectures.basics.part2oop

object Enums extends App {
  enum Permissions {
    case READ, WRITE, EXECUTE, NONE
    def openDocument(): Unit =
      if(this == READ) println("Opening document...")
      else println("Reading not allowed")
  }

  val p1 = Permissions.READ
  println(p1)
  p1.openDocument()

  Permissions.WRITE.openDocument()

  println(Permissions.READ == p1)

  enum PermissionsWithBits(val bits: Int, val level: Int) {
    case READ extends PermissionsWithBits(4, 0)
    case WRITE extends PermissionsWithBits(2, 1)
    case EXECUTE extends PermissionsWithBits(1, 2)
    case NONE extends PermissionsWithBits(0, 3)
  }

  object PermissionsWithBits {
    def fromBits(bits: Int): Option[PermissionsWithBits] = PermissionsWithBits.values.find(
      p = permission => permission.bits == bits
    )
  }

  val p2 = PermissionsWithBits.WRITE
  println(p2)
  println(p2.bits)
  println(p2.level)

  val p3 = PermissionsWithBits.fromBits(1)
  print(p3)
}
