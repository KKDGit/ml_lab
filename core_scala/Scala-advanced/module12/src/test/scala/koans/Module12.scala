
/* Copyright (C) 2010-2019 Escalate Software, LLC. All rights reserved. */

package koans

import org.scalatest.Matchers
import scala.annotation.tailrec
import scala.util.control.TailCalls._
import org.scalatest.{SeveredStackTraces, FunSpec}
import support.StopOnFirstFailure

class Module12 extends FunSpec with Matchers with StopOnFirstFailure with SeveredStackTraces {

  // using the @tailrec annotation, ensure that the following fibonacci function in the ObscureMathUtil class
  // is actually tail recursive. Make any changes necessary to get it to compile as tail recursive and ensure the
  // tests still pass

  // Note that this is one of those trick questions that you either get fairly quickly or can spend ages on.
  // Don't spend ages on it, if you don't get somewhere in about 5-10 minutes, check out the solution and
  // satisfy yourself that you understand, then move on to the next exercise.

  class ObscureMathUtil {
    def fib(num: Int): Long =
      num match {
        case 0 => 0L
        case 1 => 1L
        case x => fib(x - 1) + fib(x - 2)
      }
  }

  describe("The fibonacci function") {
    val obscureMathUtil = new ObscureMathUtil

    it ("should work for 0") {
      obscureMathUtil.fib(0) should be (0)
    }

    it ("should work for 1") {
      obscureMathUtil.fib(1) should be (1)
    }

    it ("should work for various positive values") {
      obscureMathUtil.fib(5) should be (5)
      obscureMathUtil.fib(8) should be (21)
      obscureMathUtil.fib(20) should be (6765)
    }

    it ("should map over a range of values correctly") {
      (8 to 12).map(obscureMathUtil.fib(_)) should be (Vector(21, 34, 55, 89, 144))
    }
  }

  // Using the scala.util.control.TailCalls library,
  // create a trampoline with three methods, rock, paper and scissors. For a given integer n, rock should call paper(n-1)
  // paper should call scissors(n-1) and scissors should call rock(n-1) until n is 0 in each case.
  // each call. When 0 is reached in any of the methods, the method should resolve one of the following results:
  // the computer is effectively playing Rock, so if rock(0) is reached, return Tie, if scissors(0) is reached,
  // return Lose, and if paper(0) is reached, return Win

  // as an extra-extra-credit option, remove the scala.util.control.TailCalls import and referring back
  // to the slides (or otherwise) implement the whole trampoline mechanism yourself.
  // to do this, you will need to implement a trampoline function and alter the tests to use it
  // You will need to implement your own Bounce, Call and Done objects too

  object Outcome extends Enumeration {
    val Tie, Win, Lose = Value
  }

  // Uncomment below to test your RockPaperScissors implementation
  /*describe ("Rock, Paper and Scissors game") {
    it ("should win with a value of rock(877)") {
      RockPaperScissors.rock(877).result should be (Outcome.Win)
    }

    it ("should lose and not stack overflow with a value of paper(9999997)") {
      RockPaperScissors.paper(9999997).result should be (Outcome.Lose)
    }

    it ("should tie with a value of scissors(1000)") {
      RockPaperScissors.scissors(1000).result should be (Outcome.Tie)
    }
  }*/

}
