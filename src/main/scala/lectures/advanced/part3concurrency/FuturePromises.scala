package lectures.advanced.part3concurrency

import scala.concurrent.{Future, Promise}
import concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Random, Success}
object FuturePromises extends App {
  /*
    1) fulfill a future IMMEDIATELY with a value
    2) inSequence(fa, fb)
    3) first(fa, fb) => new future with the first value of the two futures
    4) last(fa, fb) => new future with the last value
    5) retryUntil[T](action: () => Future[T], condition: T => Boolean): Future[T]
   */
  def fulfillImmediately[T](value: T): Future[T] = Future {
    println(s"Fulfilling $value")
    value
  }

  def inSequence[A, B](fa: Future[A], fb: Future[B]) = fa.flatMap(_ => fb) // I don't think this is really running them in sequence, but it's returning the results in sequence


  val f5 = fulfillImmediately(5)
  val fHamada = fulfillImmediately("Hamada")
//  inSequence(fHamada, f5)
  def first[A](fa: Future[A], fb: Future[A]) = {
    val promise = Promise[A]()
    val future = promise.future
    fa.onComplete(promise.tryComplete)
    fb.onComplete(promise.tryComplete)
    future
  }

  def last[A](fa: Future[A], fb: Future[A]) = {
    val promise = Promise[A]() // "controller" over a future
    val future = promise.future
    fa.onComplete(_ => fb.onComplete(promise.tryComplete))
    fb.onComplete(_ => fa.onComplete(promise.tryComplete))
    future
  }

  val fast = Future {
    println("Fast started")
    Thread.sleep(100)
    println("Fast finished")
    42
  }

  val slow = Future {
    println("Slow started")
    Thread.sleep(200)
    println("Slow Finished")
    45
  }
  first(fast, slow).foreach(f => println("FIRST: " + f))
  last(fast, slow).foreach(l => println("LAST: " + l))
  inSequence(fast, slow).foreach(println)
  def retryUntil[T](action: () => Future[T], condition: T => Boolean): Future[T] = {
    action().filter(condition).recoverWith {
      case e => retryUntil(action, condition)
    }
  }

  val random = new Random()
  val action = () => Future {
    Thread.sleep(10)
    val nextValue = random.nextInt(100)
    println("generated " + nextValue)
    nextValue
  }

  retryUntil(action, (x: Int) => x < 10).foreach(result => println("settled at " + result))
  Thread.sleep(10000)
}
