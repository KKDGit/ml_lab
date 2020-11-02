package com.escalatesoft.macros

import org.scalatest.{FunSpec, Matchers}


class FunctionMacrosSpec extends FunSpec with Matchers {

  describe ("The simple macro") {
    it ("should macroize something properly") {
      val fn = SimpleMacro.describeFn((x: Int) => x + 1)
      println(fn)
      val fn2 = SimpleMacro.describeFn((x: Double) => x.toString)
      println(fn2)
    }
  }

  describe ("The function1 macro") {
    import FunctionMacros._
    it ("should macroize something properly") {
      val named: NamedFunction[Int, Int] = describeFn((x: Int) => x + 1)
      println(named)
    }

    it ("should work for a function from Double to String") {
      val named = describeFn((x: Double) => x.toString)
      println(named)
    }

    it ("should work for multi lined functions") {
      val named = describeFn { (x: Double) =>
        println("we are here")
        x * x
      }
      println(named)
    }

    it ("should produce a compiler warning for very long functions") {
      val named = describeFn { (x: Double) =>
        println("A very long function definition")
        println("A very long function definition")
        println("A very long function definition")
        println("A very long function definition")
        println("A very long function definition")
        x * x
      }
      println(named)
    }

  }

}
