package lectures.basics.part2oop

object Generics extends App {
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // Variance Problem
  // If Cat extends Animal, would List[Animal] = List[Cat] work?

  // 1. Yes, Covariance
  class CovariantList[+A] {
    def add[B >: A](animal: B): ContravariantList[B] = new ContravariantList[B]
  }
  val covariantList: CovariantList[Animal] = new CovariantList[Cat]
  val newList = covariantList.add(new Dog) // -> returns a list of animal

  // 2. No, Invariance -- only the type defined at the beginning
  class InvariantList[A]
  val invariantList: InvariantList[Animal] = new InvariantList[Animal]

  // 3. Hell, no! Contravariance -- only a super class is allowed (List[Cat] = List[Animal])
  class ContravariantList[-A]
  val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal]

}
