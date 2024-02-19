package lectures.part3fb

object TuplesAndMaps extends App {
  val tuple1: (String, Int) = ("Hamada", 123)
  println(tuple1)

  val phonebook = Map(tuple1, "Daniel" -> 567).withDefaultValue(-1)
  println(phonebook)
  println(phonebook.contains("Hamada"))
  println(phonebook("Mary"))

  val newPair: (String, Int) = "Mary" -> 482
  val newPhonebook = phonebook + newPair
  println(newPhonebook)
  println(newPhonebook("Mary"))

  val filteredMap = phonebook
    .map(pair => "LOL-" + pair._1.toLowerCase -> ("+20-" + pair._2))
    .view.mapValues(v => v + "---")
    .filterKeys(k => k.contains('m'))
  println(filteredMap.get("LOL-daniel"))
  println(filteredMap.get("LOL-hamada"))

  val testMapConflict = Map("JIM" -> 456, "Jim" -> 123)
  println(testMapConflict)
  println(testMapConflict.map(pair => pair._1.toLowerCase -> pair._2))

  /*
    1.  What would happen if I had two original entries "Jim" -> 555 and "JIM" -> 900

        !!! careful with mapping keys.

    2.  Overly simplified social network based on maps
        Person = String
        - add a person to the network
        - remove
        - friend (mutual)
        - unfriend

        - number of friends of a person
        - person with most friends
        - how many people have NO friends
        - if there is a social connection between two people (direct or not)
   */
}
