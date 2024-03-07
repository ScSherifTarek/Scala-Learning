package lectures.advanced.part5ts

object Variance extends App {

  /**
   * 1. Invariant, covariant, contravariant
   *   Parking[T](things: List[T]) {
   *     park(vehicle: T)
   *     impound(vehicles: List[T])
   *     checkVehicles(conditions: String): List[T]
   *   }
   *
   * 2. used someone else's API: IList[T]
   * 3. Parking = monad!
   *     - flatMap
   */
  class Vehicle
  class Bike extends Vehicle
  class Car extends Vehicle

  class IList[T]

  class IParking[T](things: List[T]) {
      def park(vehicle: T): IParking[T] = ???
      def impound(vehicles: List[T]): IParking[T] = ???
      def checkVehicles(conditions: String): List[T] = ???
      def flatMap[B](f: T => B): IParking[B] = ???
  }

  class CParking[+T](things: List[T]) {
    def park[B >: T](vehicle: B): CParking[B] = ???
    def impound[B >: T](vehicles: List[B]): CParking[B] = ???
    def checkVehicles(conditions: String): List[T] = ???
    def flatMap[B](f: T => B): CParking[B] = ???
  }

  class XParking[-T](things: List[T]) {
    def park(vehicle: T): XParking[T] = ???

    def impound(vehicles: List[T]): XParking[T] = ???

    def checkVehicles[B <: T](conditions: String): List[B] = ???
    def flatMap[A <: T, B](f: A => B): XParking[B] = ???
  }

}
