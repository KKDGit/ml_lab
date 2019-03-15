import java.io.{Closeable, PrintWriter}
import java.time._

import StandardLogger.{error, info, warn}

import scala.util.Try

// selfless traits

trait Logger {
  def message(msg: String): String

  def info(msg: String): Unit =
    println("INFO: " + message(msg))

  def warn(msg: String): Unit =
    println("WARN: " + message(msg))

  def error(msg: String): Unit =
    println("ERROR: " + message(msg))
}

trait StandardLogger extends Logger {
  override def message(msg: String): String = msg
}

class Demo extends StandardLogger {
  def testLogging(): Unit = {
    info("This is an info")
    warn("This is a warning")
    error("This is an error")
  }
}

(new Demo).testLogging()

// because it's selfless

object StandardLogger extends StandardLogger

class Demo2 {
  import StandardLogger._
  def testLogging(): Unit = {
    info("This is an info")
    warn("This is a warning")
    error("This is an error")
  }
}

(new Demo2).testLogging()

// stackable traits

trait DateLogger extends Logger {
  abstract override def message(msg: String): String =
    s"${LocalDateTime.now()}: ${super.message(msg)}"
}

class Demo3 extends StandardLogger with DateLogger {
  def testLogging(): Unit = {
    info("This is an info")
    warn("This is a warning")
    error("This is an error")
  }
}

(new Demo3).testLogging()

// interface injection

class Door {
  def close(): Unit = println("SLAM!")
}

def closeAll(items: Seq[Closeable]): Unit = {
  for (item <- items) yield Try(item.close())
}

// note that door is not closeable, but I can do this:

val door1 = new Door with Closeable
val door2 = new Door with Closeable
val os = new PrintWriter("temp.txt")

closeAll(Seq(door1, os, door2))
