
/* Copyright (C) 2010-2017 Escalate Software, LLC. All rights reserved. */

package koans

import org.scalatest.Matchers
import scala.collection._
import org.scalatest.{FunSuite, SeveredStackTraces}
import support.StopOnFirstFailure

import scala.util.control.NonFatal

class Module08 extends FunSuite with Matchers with StopOnFirstFailure with SeveredStackTraces {

  case class Passenger(name: String, cellPhoneNumber: String)
  case class Carriage(passengers: List[Passenger])
  case class Train(name: String, carriages: List[Carriage])
  case class Route(name: String, activeTrain: Train)

  test ("Get deep list of optional fields") {
    // find any cell phone numbers provided for all customers on a given track. Note that for any given
    // route, there may or may not be an active train, for that train there will be a sequence of carriages,
    // int the carriages there will be a list of passengers, and each passenger may, or may not,
    // specify their cell phone number.
    //
    // At present, this is written in the idiomatic Java style, using nulls
    // for the optional fields when they are absent. Convert the representations above to idiomatic scala,
    // and the function below to use idiomatic scala as well, ensure that the tests still pass (note, you
    // will need to change the testSetup function a little as well for the new data representation.

    def testSetup(): Seq[Route] = {
      val route1 = Route("Glen Gach to Glen Pach",
        Train("The Flying Scotsman",
          List(Carriage(List(Passenger("Rob Roy", "121-212-1212"), Passenger("Connor McCleod", null))),
               Carriage(List(Passenger("Joey McDougall", "454-545-4545"))))
        )
      )

      val route2 = Route("Defuncto 1", null)

      val route3 = Route("Busy Route of Luddites",
        Train("The Tech Express",
          List(Carriage(List(Passenger("Ug", null), Passenger("Glug", null))),
               Carriage(Nil),
               Carriage(List(Passenger("Smug", "323-232-3232"))))
        )
      )

      List(route1, route2, route3)
    }

    def allCellNumbersForPassengers(routes: Seq[Route]): Seq[String] = {
      val cellNumbers = mutable.ListBuffer.empty[String]
      for (route <- routes) {
        if (route.activeTrain != null) {
          val train = route.activeTrain
          for (carriage <- train.carriages) {
            for (passenger <- carriage.passengers) {
              if (passenger.cellPhoneNumber != null) cellNumbers += passenger.cellPhoneNumber
            }
          }
        }
      }
      cellNumbers
    }

    // now do the actual test
    val cellList = allCellNumbersForPassengers(testSetup)

    cellList.size should be (3)
    cellList should contain ("121-212-1212")
    cellList should contain ("454-545-4545")
    cellList should contain ("323-232-3232")
  }


  test("Count the vowels") {
    // write an idiomatic scala method to count the vowels in a given string so that the tests below pass.
    def countVowels(str: String): Int = {
      // fill this in with something real
      0
    }

    countVowels("countVowels") should be (4)
    countVowels("are you feeling OK?") should be (8)
    countVowels("aEIoU") should be (5)
    countVowels("L33t Sp3@k") should be (0)
  }

  /*
   * A more fun exercise now. Using the Either class (suggested, you are free to find another way
   * if you feel bold, but I suggest Either), write a type parameterized class and companion object
   * that provide the following:
   *
   * An internal Either class for String and whatever type is in the parameter A. The string will be used to
   * record any errors in our work flow. Do you need any co or contra variance?
   *
   * A map method that takes a function from parameterized type A to parameterized type B, returning another
   * MaybeError with either the error string, or the new value of type B. If an exception occurs in the map
   * function, put the exception toString into the MaybeError string parameter so it propagates through the rest
   * of the workflow
   *
   * A method value that returns whatever the value is, if not an error, or throws an IllegalStateException
   * with the original exception's toString in it if the MaybeError is in the error state
   *
   * A method error that returns an Option[String] with either an error in it (if in error state) or None
   * if the MaybeError is not in the error state and has a valid value
   *
   * A toString that hands back either "Error(xxx)" where xxx is the error message or "Value(yyy)" where yyy is
   * the valid value
   *
   * The companion object should have two convenience factory methods: Error to create a MaybeError in the Error
   * state with a given string message, or Value to create a MaybeError in the valid value state.
   *
   * Then, uncomment the tests below and get them to work.
   */

  /*test("MaybeError work flow") {
    val i1 = MaybeError.Value("10")
    val i2 = i1.map(v => v.toInt)
    val i3 = i2.map(v => v * 3)
    i1.toString should be ("Value(10)")
    i2.toString should be ("Value(10)")
    i3.toString should be ("Value(30)")
    i3.error should be (None)
    i3.value should be (30)

    // now for a broken workflow
    val n1 = MaybeError.Value("hello")
    val n2 = n1.map(v => v.toInt)
    val n3 = n2.map(v => v * 3)
    n1.toString should be ("Value(hello)")
    n2.toString should be ("Error(java.lang.NumberFormatException: For input string: \"hello\")")
    n3.toString should be ("Error(java.lang.NumberFormatException: For input string: \"hello\")")
    n3.error should be (Some("java.lang.NumberFormatException: For input string: \"hello\""))
    intercept[IllegalStateException] { n3.value }
  }*/

  // What you just did with constructing this work flow for MaybeError has a larger, scarier name in the Scala
  // world. You have probably heard it and wondered what it is. Can you name it? Well, you just wrote one...

  // mini-sudoku

  // we are going to perform a bunch of operations that might be used in a sudoku game, for the sake of
  // brevity, we will use a 4x4 sudoku board rather than a 9x9 one, but the same functions will work for both
  // with only a little reworking (if any)

  // first, define an idiomatic scala method to test, given a list of 4 numbers, whether the solution for that
  // list is "good", i.e whether the numbers 1 to 4 appear in the list only once and all 4 are used
  // if numbers greater than 4 or less than 1 exist, the function should return false
  // if the list is not complete, or has duplicates, the function should return false

  def isGood(numberRow: Seq[Int]): Boolean = false  // you need to replace this with a real function

  test("isGood function works") {
    // isGood should pass all of these tests
    isGood(Nil) should be (false)
    isGood(List(1,2,3,4)) should be (true)
    isGood(List(1)) should be (false)
    isGood(List(1,2,2,4)) should be (false)
    isGood(List(4,1,3,2)) should be (true)
    isGood(List(3,2,1)) should be (false)
    isGood(List(1,2,3,5)) should be (false)
    isGood(List(0,2,3,4)) should be (false)
  }

  // next, we need a transpose function that given a rectangular seq of seq of ints, transposes that rectangle
  // so that columns become rows and rows become columns. It should pass the following tests

  def transpose(matrix: Seq[Seq[Int]]): Seq[Seq[Int]] = matrix   // again, replace this

  test("transpose should, well, transpose matrices") {
    transpose(Nil) should be (Nil)
    transpose(Seq(Seq(1))) should be (Seq(Seq(1)))
    transpose(Seq(Seq(1,2))) should be (Seq(Seq(1),Seq(2)))
    transpose(Seq(Seq(1,2), Seq(3,4), Seq(5,6), Seq(7,8))) should be (Seq(Seq(1,3,5,7), Seq(2,4,6,8)))
    transpose(Seq(Seq(1,2,3), Seq(4,5,6), Seq(7,8,9))) should be (Seq(Seq(1,4,7), Seq(2,5,8), Seq(3,6,9)))
    intercept[IllegalArgumentException] {
      transpose(Seq(Seq(1,2,3), Seq(4,5,6,7)))
    }
  }

  // now define a group2by2 function, which should ensure a 4x4 matrix passed in (Seq of Seqs), and then return
  // a sequence of 4 sequences, with the top left, top right, bottom left and bottom right corner numbers in it,
  // in other words, it should, given this:
  //
  // 1  2  3  4
  // 5  6  7  8
  // 9  10 11 12
  // 13 14 15 16
  //
  // return
  //
  // 1 2 5 6
  // 3 4 7 8
  // 9 10 13 14
  // 11 12 15 16
  // indeed, this is one of the tests it should satisfy.
  // You should be able to do this without needing any indices in a for loop, i.e. no for(i <- 0 to 1) stuff
  // and indexing into the list. Hint, you may want to use your
  // transpose function above, along with the built in functions on Seq to split up the lists

  def group2x2(board: Seq[Seq[Int]]): Seq[Seq[Int]] = board   // And, replace this too

  test("group2x2 should do its job") {
    intercept[IllegalArgumentException] {
      group2x2(Nil)
    }
    intercept[IllegalArgumentException] {
      group2x2(Seq(Seq(1,2,3,4), Seq(5,6,7,8), Seq(9,10,11,12)))
    }
    intercept[IllegalArgumentException] {
      group2x2(Seq(Seq(1,2,3), Seq(5,6,7), Seq(9,10,11), Seq(13,14,15)))
    }
    intercept[IllegalArgumentException] {
      group2x2(Seq(Seq(1,2,3,4), Seq(5,6,7,8), Seq(9,10,11), Seq(13,14,15,16)))
    }

    group2x2(Seq(Seq(1,2,3,4), Seq(5,6,7,8), Seq(9,10,11,12), Seq(13,14,15,16))) should be (
      Seq(Seq(1,2,5,6), Seq(3,4,7,8), Seq(9,10,13,14), Seq(11,12,15,16)))

    group2x2(Seq(Seq(1,2,1,2), Seq(1,2,1,2), Seq(1,2,1,2), Seq(1,2,1,2))) should be (
      Seq(Seq(1,2,1,2), Seq(1,2,1,2), Seq(1,2,1,2), Seq(1,2,1,2)))
  }

  // Extra credit
  // Pick a piece of code that you have written recently, perhaps in Java, perhaps in Scala, and add (potentially
  // a simplified or isolated version of) it here. Write some tests to exercise it, then see if you can sweep
  // through it and apply style improvements on the code. Having a good set of tests will ensure that your
  // improved style doesn't actually break anything
  
}
