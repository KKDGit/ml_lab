val o1: Option[Int] = Some(1)
val o2: Option[Int] = Some(2)
val o3: Option[Int] = None

def addOptionalInts(oa: Option[Int], ob: Option[Int]): Option[Int] = {
  for {
    a <- oa
    b <- ob
  } yield a + b
}

addOptionalInts(o1, o2)
addOptionalInts(o2, o3)

object Options {
  def zip[A, B](o1: Option[A], o2: Option[B]): Option[(A, B)] = {
    (o1, o2) match {
      case (Some(a), Some(b)) => Some(a, b)
      case _ => None
    }
  }

  def zipWith[A, B, R](o1: Option[A], o2: Option[B])(fn: (A, B) => R): Option[R] = {
    zip(o1, o2) match {
      case Some((a, b)) => Some(fn(a, b))
      case _ => None
    }
  }
}

Options.zip(o1, o2)
Options.zip(o2, o3)

Options.zipWith(o1, o2)(_ + _)
Options.zipWith(o2, o3)(_ + _)

import cats._
import cats.data._
import cats.implicits._

// cartesian syntax
(o1 |@| o2).map(_ + _)

// but that's old and awkward, here's the new way
(o1, o2).mapN(_ + _)

(o2, o3).mapN(_ + _)

import scala.concurrent._
import ExecutionContext.Implicits.global
import duration._

val f1 = Future(1)
val f2 = Future(2)

val f3 = (f1, f2).mapN(_ + _)
Await.result(f3, 1.second)
