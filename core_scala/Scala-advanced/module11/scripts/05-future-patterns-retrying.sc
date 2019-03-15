import scala.concurrent._
import duration._
import ExecutionContext.Implicits.global
import scala.util.Try

var time = 0

def calc(): Int = {
  if (time > 3) 10 else {
    time += 1
    throw new IllegalStateException("not yet")
  }
}

def fCalc(): Future[Int] = Future(calc())

def resetTries(): Unit = time = 0

Try(calc())
Try(calc())
Try(calc())
Try(calc())
Try(calc())

resetTries() // back to not working

val f1 = fCalc().
  fallbackTo(fCalc()).
  fallbackTo(fCalc()).
  fallbackTo(fCalc())

Await.ready(f1, 10.seconds)


resetTries() // back to not working

val f2 = fCalc().
  fallbackTo(fCalc()).
  fallbackTo(fCalc()).
  fallbackTo(fCalc()).
  fallbackTo(fCalc())

Await.ready(f2, 10.seconds)

resetTries()

def retry[T](op: => T, retries: Int): Future[T] =
  Future(op) recoverWith { case _ if retries > 0 => retry(op, retries - 1) }

val f3 = retry(calc(), 3)
Await.ready(f3, 10.seconds)

resetTries()

val f4 = retry(calc(), 5)
Await.ready(f4, 10.seconds)


import akka.actor._
import akka.pattern.after

val as = ActorSystem("timer")

val scheduler = as.scheduler
val ec: ExecutionContext = as.dispatcher

def retryBackoff[T](op: => T, backoffs: Seq[FiniteDuration]): Future[T] =
  Future(op)(ec) recoverWith {
    case _ if backoffs.nonEmpty =>
      after(backoffs.head, scheduler)(retryBackoff(op, backoffs.tail))
  }

resetTries()

val f5 = retryBackoff(calc(), Seq(500.millis, 500.millis, 1.second, 1.second, 2.seconds))

Await.result(f5, 2.minutes)
