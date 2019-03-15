package org.own.concepts

import scala.util.{Failure, Success, Try}

case class TryConcept() {155
  def divide: Try[Int] = {
    val dividend = Try(Console.readLine("Enter an Int that you'd like to divide:\n").toInt)
    val divisor = Try(Console.readLine("Enter an Int that you'd like to divide by:\n").toInt)
    val problem = dividend.flatMap(x => divisor.map(y => x/y))
    problem match {
      case Success(v) =>
        println("Result of " + dividend.get + "/"+ divisor.get +" is: " + v)
        Success(v)
      case Failure(e) =>
        println("You must've divided by zero or entered something that's not an Int. Try again!")
        println("Info from the exception: " + e.getMessage)
        divide
    }
  }
}

object runTry {
  val tryConcept = new TryConcept
  def main(args: Array[String]): Unit = {
    tryConcept.divide
  }
}