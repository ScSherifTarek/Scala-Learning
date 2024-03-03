package lectures.advanced.part3concurrency

object ConcurrencyProblems extends App {
  /**
   * Exercises
   * 1 - create "inception threads"
   * thread 1
   * -> thread 2
   * -> thread 3
   * ....
   * each thread prints "hello from thread $i"
   * Print all messages IN REVERSE ORDER
   *
   * 2 - What's the max/min value of x?
   * 3 - "sleep fallacy": what's the value of message?
   */
  def inceptionThreads(n: Int, i: Int = 1): Unit = {
    if(i > n) return

    val thread = new Thread(() => inceptionThreads(n, i + 1))
    thread.start()
    thread.join()
    println(s"Hello from thread ${i}")
  }
  inceptionThreads(2)

  // [1, 100]
  def minMaxX(): Unit = {
    var x = 0
    val threads = (1 to 100).map(_ => new Thread(() => x += 1))
    threads.foreach(_.start())
  }
//  (1 to 100).foreach(_ => minMaxX())

  def demoSleepFallacy(): Unit = {
    var message = ""
    val awesomeThread = new Thread(() => {
      Thread.sleep(1000)
      message = "Scala is awesome"
    })

    message = "Scala sucks"
    awesomeThread.start()
    Thread.sleep(1001)
    println(message)
  }
  (1 to 100).foreach(_ => demoSleepFallacy())
}
