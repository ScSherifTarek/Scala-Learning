package lectures.part3fb

import java.util.Random
import scala.util.Try

object HandlingFailure extends App {
  /*
      Exercise
     */
  val host = "localhost"
  val port = "8080"

  def renderHTML(page: String) = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Connection interrupted")
    }

    def safeGet(url: String): Try[String] = Try(get(url))
  }

  object HttpService {
    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection =
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("Someone else took the port")

    def safeGetConnection(host: String, port: String): Try[Connection] = Try(getConnection(host, port))
  }

  // if you get the html page from the connection, print it to the console i.e. call renderHTML
  Try(HttpService.getConnection(host, port)).map(_.get("My website")).foreach(println)

  for {
    connection <- Try(HttpService.getConnection(host, port))
    website <- Try(connection.get("My website"))
  } println(website)

  for {
    connection <- HttpService.safeGetConnection(host, port)
    website <- connection.safeGet("My website")
  } renderHTML(website)
}
