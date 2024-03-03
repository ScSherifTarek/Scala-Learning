package lectures.advanced.part3concurrency

import scala.collection.mutable
import scala.util.{Random, Try}


object ThreadCommunication extends App {

  /*
    the producer-consumer problem

    producer -> [ ? ] -> consumer
   */
  class SimpleContainer {
    private var value: Int = 0

    def isEmpty: Boolean = value == 0
    def set(newValue: Int) = value = newValue
    def get = {
      val result = value
      value = 0
      result
    }
  }

  def naiveProdCons(): Unit = {
    val container = new SimpleContainer

    val consumer = new Thread(() => {
      println("[consumer] waiting...")
      while(container.isEmpty) {
        println("[consumer] actively waiting...")
      }

      println("[consumer] I have consumed " +  container.get)
    })

    val producer = new Thread(() => {
      println("[producer] computing...")
      Thread.sleep(500)
      val value = 42
      println("[producer] I have produced, after long work, the value " + value)
      container.set(value)
    })

    consumer.start()
    producer.start()
  }

  // naiveProdCons()

  // wait and notify
  def smartProdCons(): Unit = {
    val container = new SimpleContainer

    val consumer = new Thread(() => {
      println("[consumer] waiting...")
      container.synchronized {
        container.wait()
      }

      // container must have some value
      println("[consumer] I have consumed " + container.get)
    })

    val producer = new Thread(() => {
      println("[producer] Hard at work...")
      Thread.sleep(2000)
      val value = 42

      container.synchronized {
        println("[producer] I'm producing " + value)
        container.set(value)
        container.notify()
      }
    })

//    producer.join() // if producer started and finished before consumer, that would cause the consumer to stuck
    consumer.start()
    producer.start()
  }

//  smartProdCons()

  def prodConsLargeBuffer(): Unit = {
    val buffer: mutable.Queue[Int] = new mutable.Queue[Int]
    val capacity = 3

    val consumers = (1 to 3).map(c => {
      new Thread(() => {
        val random = new Random()

        while (true) {
          buffer.synchronized {
            while (buffer.isEmpty) {
              println(s"[consumer(${c})] buffer empty, waiting...")
              buffer.wait()
            }

            // there must be at least ONE value in the buffer
            val x = buffer.dequeue()
            println(s"[consumer(${c})] consumed " + x)

            // hey producer, there's empty space available, are you lazy?!
            buffer.notify()
          }

          Thread.sleep(random.nextInt(250))
        }
      })
    })

    val producers = (1 to 3).map(p => {
      new Thread(() => {
        val random = new Random()
        var i = 0

        while (true) {
          buffer.synchronized {
            while (buffer.size == capacity) {
              println(s"[producer(${p})] buffer is full, waiting...")
              buffer.wait()
            }

            // there must be at least ONE EMPTY SPACE in the buffer
            println(s"[producer(${p})] producing " + i)
            buffer.enqueue(i)

            // hey consumer, new food for you!
            buffer.notify()

            i += 1
          }

          Thread.sleep(random.nextInt(100))
        }
      })
    })

    consumers.foreach(_.start())
    producers.foreach(_.start())
  }
  prodConsLargeBuffer()
}
