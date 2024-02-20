package lectures.basics.part3fb

import java.util.Random

object Options extends App {
  /*
    Exercise.
   */
  val config: Map[String, String] = Map(
    // fetched from elsewhere
    "host" -> "176.45.36.1",
    "port" -> "80"
  )

  class Connection {
    def connect = "Connected" // connect to some server
  }

  object Connection {
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }

  val host = config.get("host")
  val port = config.get("port")

  println("C1: ")
  val c1 = if(host.nonEmpty && port.nonEmpty) Connection(host.get, port.get) else None
  if(c1.nonEmpty) {
    println(c1.get.connect)
  } else {
    println("Not Connected")
  }

  println("C2: ")
  val c2 = host.flatMap(h => port.flatMap(p => Connection(h, p)))
  c2.map(_.connect).orElse(Option("Not Connected")).foreach(println)

  println("C3: ")
  val c3 = for {
    h <- config.get("host")
    p <- config.get("port")
    c <- Connection(h, p)
  } yield c
  c3.map(_.connect).orElse(Option("Not Connected")).foreach(println)
}
