package lectures.advanced.part2fp

object Monads extends App {
  /*
    EXERCISE:
    1) implement a Lazy[T] monad = computation which will only be executed when it's needed.
      unit/apply
      flatMap

    2) Monads = unit + flatMap
       Monads = unit + map + flatten

       Monad[T] {

        def flatMap[B](f: T => Monad[B]): Monad[B] = ... (implemented)

        def map[B](f: T => B): Monad[B] = ???
        def flatten(m: Monad[Monad[T]]): Monad[T] = ???

        (have List in mind)
   */
  class Lazy[T](computation: => T) {
    lazy val value: T = computation
    def flatMap[B](f: (=> T) => Lazy[B]): Lazy[B] = f(value)
    def use: T = value
    def map[B](f: (=> T) => B): Lazy[B] = flatMap(x => Lazy(f(x)))
  }
  object Lazy {
    def apply[T](computation: => T): Lazy[T] = new Lazy(computation)
  }

  val lazyInstance = Lazy {
    println("Today I don't feel like doing anything")
    42
  }

  val flatMappedInstance = lazyInstance.flatMap(x => Lazy {
    10 * x
  })
  val flatMappedInstance2 = lazyInstance.flatMap(x => Lazy {
    10 * x
  })

  val res = flatMappedInstance.map(_*10).map(_+10)
//  println(res.use)
  def flatten[T](m: Lazy[Lazy[T]]): Lazy[T] = m.flatMap(x => x)

  val lazyOfLazy = Lazy(lazyInstance)
  println(lazyOfLazy.use)
  println(flatten(lazyOfLazy).use)
}
