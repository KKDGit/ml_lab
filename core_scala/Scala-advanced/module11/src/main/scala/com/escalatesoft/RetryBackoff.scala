package com.escalatesoft

import scala.concurrent._
import scala.concurrent.duration._

object RetryBackoff extends App {
  import akka.actor._
  import akka.pattern.after

  val as = ActorSystem("timer")

  val scheduler: Scheduler = as.scheduler
  val ec: ExecutionContext = as.dispatcher

  def retryBackoff[T](op: => T, backoffs: Seq[FiniteDuration])(implicit s: Scheduler, ec: ExecutionContext): Future[T] =
    Future(op)(ec) recoverWith {
      case _ if backoffs.nonEmpty =>
        after(backoffs.head, scheduler)(retryBackoff(op, backoffs.tail))
    }

  var time = 0

  def calc(): Int = {
    if (time > 3) 10 else {
      time += 1
      println("Not yet!")
      throw new IllegalStateException("not yet")
    }
  }

  val f5 = retryBackoff(calc(), Seq(500.millis, 500.millis, 1.second, 1.second, 2.seconds))(scheduler, ec)
  println(Await.ready(f5, 10.seconds))

  as.terminate() // remember to terminate the actor system or your app will never exit
}