
import scala.util._
import scala.util.control.NonFatal

val d1 = Try(2/1)
val d2 = Try(2/0)

val m1 = for (x <- d1) yield x * 2
val m2 = for (x <- d2) yield x * 2

val m3 = for {
  a <- m1
  b <- m2
} yield a * b

m1.isFailure
m2.isFailure

m1.getOrElse(0)
m2.getOrElse(0)

m1.recover {
  case _: ArithmeticException => 0
}

m2.recover {
  case _: ArithmeticException => 0
}

m1 match {
  case Success(x) => x * 2
  case Failure(th) =>
    println(th.getMessage)
    0
}

m2 match {
  case Success(x) => x * 2
  case Failure(th) =>
    println(th.getMessage)
    0
}

m1.get
//m2.get


val e1: Either[ArithmeticException, Int] = Right(2)
val e2: Either[ArithmeticException, Int] = Left(new ArithmeticException("/ by zero"))

val em1 = for (x <- e1) yield x * 2
val em2 = for (x <- e2) yield x * 2

em1.right.getOrElse(0)
em2.right.getOrElse(0)

em1 match {
  case Right(x) => x * 2
  case Left(th) =>
    println(th.getMessage)
    0
}

em2 match {
  case Right(x) => x * 2
  case Left(th) =>
    println(th.getMessage)
    0
}

import org.scalactic._

def makeInt(str: String): Int Or ErrorMessage = {
  try {
    Good(str.toInt)
  } catch {
    case NonFatal(_) => Bad("Invalid Number: " + str)
  }
}

val or1 = makeInt("1")
val or2 = makeInt("uno")

for (x <- or1) yield x * 2
for (x <- or2) yield x * 2

