
/* Copyright (C) 2010-2019 Escalate Software, LLC. All rights reserved. */

package koans

import java.time.LocalDate

import org.scalatest.Matchers

import scala.annotation.tailrec
import scala.util.control.TailCalls._
import org.scalatest.{FunSpec, SeveredStackTraces}
import support.StopOnFirstFailure

class Module13 extends FunSpec with Matchers with StopOnFirstFailure with SeveredStackTraces {

  // Make a Monad!
  // Create a Maybe data structure that behaves just like Option in Scala. It should have two
  // subtypes, Just and Empty, Just takes an item and returns a "full maybe" while Empty returns
  // an empty maybe. Write the isDefined, map and flatMap methods so that the following tests compile
  // and pass. The Maybe abstract class has been provided to get you going, but you should create
  // the two subclasses and then uncomment the tests. Be sure to understand everything that is going
  // on here to make the for expression work. Also, really read through the tests, particularly
  // the for comprehension at the end. You just made that work! Give yourself a pat on the back.

  sealed abstract class Maybe[+T] {
    def isDefined: Boolean
    def get: T
    def map[U](fn: T => U): Maybe[U]
    def flatMap[U](fn: T => Maybe[U]): Maybe[U]
  }

  // Put your definitions for Empty and Just[T] here...

  // uncomment these and get them compiling and passing
  /*describe ("Our wonderful Maybe monad") {
    case class Address(street: String, zip: Maybe[String])
    case class Person(name: String, address: Maybe[Address])

    val vagrantJoe = Person("Poor Joe", Empty)
    val noZipKip = Person("Kip", Just(Address("123 Mediocre Way", Empty)))
    val luckyJill = Person("Rich Jill", Just(Address("1 Loadsamoney Way", Just("94121"))))

    val maybePersonSeq: Seq[Maybe[Person]] =
      Seq(Just(vagrantJoe), Empty, Just(noZipKip), Empty, Just(luckyJill))

    it ("should work with map") {
      val street1 = vagrantJoe.address.map(_.street)
      street1.isDefined should be (false)
      intercept[IllegalStateException] {
        street1.get
      }

      val street2 = noZipKip.address.map(_.street)
      street2.isDefined should be (true)
      street2.get should be ("123 Mediocre Way")

      val street3 = luckyJill.address.map(_.street)
      street3.isDefined should be (true)
      street3.get should be ("1 Loadsamoney Way")
    }

    it ("should work with flatMap") {
      val zip1 = vagrantJoe.address.flatMap(_.zip)
      zip1.isDefined should be (false)
      intercept[IllegalStateException] {
        zip1.get
      }

      val zip2 = noZipKip.address.flatMap(_.zip)
      zip2.isDefined should be (false)
      intercept[IllegalStateException] {
        zip2.get
      }

      val zip3 = luckyJill.address.flatMap(_.zip)
      zip3.isDefined should be (true)
      zip3.get should be ("94121")
    }

    it ("should work with a for expression") {

      // why two separate fors? what happens if you try to combine them?
      // Hint, to understand why, maybe rewrite the expression using map and flatMap (as Scala would)
      // and follow what happens to the types
      val zips = for (maybePerson <- maybePersonSeq) yield {
        for {
          person <- maybePerson
          address <- person.address
          zip <- address.zip
        } yield zip
      }

      zips.size should be (5) // because we haven't removed the empties
      val justZips = zips.filter(_.isDefined)
      justZips.size should be (1)
      justZips.head.get should be ("94121")

      // is Maybe a true monad? - can you demonstrate by writing tests that exercise the three monad laws?
    }
  }*/


  // extra-credit, using (perhaps) the Option implementation as a guide, implement
  // withFilter on the Maybe monad we created above so that you can use guards (if statements inside
  // of for comprehensions) with Maybe. Write a test to make sure it works, for example use a test that
  // the address must start with 1 in order for the comprehension to proceed. Test your new
  // implementation with some suitable test data to exercise the guard and make sure it works.

  // extra-extra-credit, add a zip to your Maybe to zip two Maybes into a single Maybe with a Tuple2 of the contents
  // or Empty if either side is Empty. Add a second zipWith method that uses the above zip and then maps a
  // function of (A, B) => R to return a new Maybe[R]. Test those below.


  // And if you want the practice, take the Free implementation from the 05-free.sc file, bring it here,
  // then add a new operation, count, which counts the number of items matching a predicate. Make sure that
  // count can be used in a for expression, and then add the handler into the interpreter for that operation
  // your count operation should take a predicate as a PartialFunction from Any => Boolean, if
  // the partial function is not defined for the input value, the predicate should be considered false
  // (not counted) in the implementation.
  // Write a test to endure that count works correctly
  // Also, put something in the database that is not a Person so that you can check if your
  // implementation works correctly when it comes across a different type than the partial function
  // is defined for.
  //
  // Can you make your count method take a generic type parameter and a regular Function1 predicate of
  // that type parameter to Boolean, to make definition of the count predicate in the API
  // more convenient? E.g. def count[T](predicate: T => Boolean): DB[Int] (hint, you will
  // probably still need to use a PartialFunction in the case class representing Count)

}
