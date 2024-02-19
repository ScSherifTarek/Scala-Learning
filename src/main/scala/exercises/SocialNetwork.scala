package exercises

import scala.annotation.tailrec

case class SocialNetwork(network: Map[String, Set[String]] = Map[String, Set[String]]().withDefaultValue(Set[String]())) {
  def add(person: String): SocialNetwork =
    SocialNetwork(network + (person -> Set[String]()))

  def remove(person: String): SocialNetwork =
    SocialNetwork(
      network
        .filter(pair => pair._1 != person)
        .map(pair => pair._1 -> pair._2.filter(_ != person))
    )

  def friend(person1: String, person2: String): SocialNetwork =
    SocialNetwork(
      network.map(pair =>
        if (pair._1 == person1) pair._1 -> (pair._2 + person2)
        else if (pair._1 == person2) pair._1 -> (pair._2 + person1)
        else pair
      )
    )

  def unfriend(person1: String, person2: String): SocialNetwork =
    SocialNetwork(
      network.map(pair =>
        if (pair._1 == person1) pair._1 -> (pair._2 - person2)
        else if (pair._1 == person2) pair._1 -> (pair._2 - person1)
        else pair
      )
    )

  def getNumOfFriends(person: String): Int =
    network(person).size

  def getPersonWithMostFriends: String =
    network.maxBy(pair => pair._2.size)._1

  def countHavingNoFriends(): Int =
    network.count(_._2.isEmpty)

  def areConnected(source: String, target: String): Boolean = {
//    def helper(connection: String, visited: Set[String] = Set[String]()): Boolean =
//      if (visited.contains(connection)) false
//      else if (network(connection).contains(target)) true
//      else {
//        val newVisited = visited + connection
//        network(connection).exists(subConnection => helper(subConnection, newVisited))
//      }

    @tailrec
    def bfs(visited: Set[String], toVisit: Set[String]): Boolean =
      if(toVisit.isEmpty) false
      else if(toVisit.contains(target)) true
      else {
        val connection = toVisit.head
        if(visited.contains(connection)) bfs(visited, toVisit.tail)
        else bfs(visited + connection, toVisit.tail ++ network(connection).diff(visited))
      }
    bfs(Set[String](), Set[String]() + source)
  }
}
object TestNetwork extends App {
  val network = SocialNetwork()
    .add("Sherif")
    .add("Ahmed")
    .add("Mohamed")
    .add("Tamer")
    .add("Hamada")
    .add("LOL")
    .add("Ayman")
    .friend("Sherif", "Ahmed")
    .friend("Ahmed", "Mohamed")
    .friend("Ahmed", "Tamer")
    .friend("Mohamed", "Hamada")
    .friend("Tamer", "Hamada")


  println(network)
  println(network.getNumOfFriends("Sherif"))
  println(network.countHavingNoFriends())
  println(network.areConnected("Sherif", "LOL"))
  println(network.areConnected("Sherif", "Hamada"))
  println(network.getPersonWithMostFriends)

  val networkWithoutAhmed = network.remove("Ahmed")
  println(networkWithoutAhmed)
  println(networkWithoutAhmed.getNumOfFriends("Sherif"))
  println(networkWithoutAhmed.countHavingNoFriends())
  println(networkWithoutAhmed.areConnected("Sherif", "LOL"))
  println(networkWithoutAhmed.areConnected("Sherif", "Mohamed"))
  println(networkWithoutAhmed.getPersonWithMostFriends)
}
