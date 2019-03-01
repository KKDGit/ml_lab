trait Logging {
  def error(msg: String): Unit = println(s"Error: $msg")
  def warn(msg: String): Unit = println(s"Warn: $msg")
  def info(msg: String): Unit = println(s"Info: $msg")
}

object Logging extends Logging

class Process1 extends Logging {
  def doIt(): Unit = {
    info("Checking the cell structure")
    error("It's all gone pear shaped")
  }
}

val p1 = new Process1
p1.doIt()

class Process2 {
  import Logging._

  def doIt(): Unit = {
    info("Checking the cell structure")
    error("It's all gone pear shaped")
  }
}

val p2 = new Process2
p2.doIt()