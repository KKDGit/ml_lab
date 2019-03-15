package koans

import java.io.File
import java.util.NoSuchElementException

import org.scalamock.scalatest.MockFactory
import org.scalatest.prop.PropertyChecks
import org.scalatest.{FunSpec, Matchers}

import scala.io.BufferedSource

class Module11 extends FunSpec with Matchers with MockFactory with PropertyChecks {

  class FileReader(file: File) {
    lazy private val source: BufferedSource = scala.io.Source.fromFile(file)
    lazy private val lines = source.getLines()
    def nextLine(): String = lines.next()
    def close(): Unit = source.close()
  }

  def withFirstLineOfFile[A](fileReader: FileReader)(fn: String => A): A = {
    try {
      fn(fileReader.nextLine())
    }
    finally fileReader.close()
  }

  describe ("The loan pattern for a file") {

    // using Scalamock, mock the FileReader out and make it return "To be or not to be" on a nextLine call,
    // and just expects a single call to close() as well, then test by replacing ??? below with the mock
    // you made.

    it ("should call a function with the known contents of a file") {
      val x = withFirstLineOfFile(???)(identity)

      x should be ("To be or not to be")
    }

    // Next, using a mock make the nextLine call result in a NoSuchElementException("next on empty iterator") being thown
    // but still ensure that the close() method is called even though the exception is thrown.
    // again replace ??? with the mock you created

    it ("should always call close() on the file, even if the file is empty causing a NoSuchElementException") {
      val ex = intercept[NoSuchElementException] {
        withFirstLineOfFile(???)(identity)
      }

      ex.getMessage should be ("next on empty iterator")
    }
  }

  def stabilityChecker(seq: String): Double = {
    val length = seq.length
    if (length == 0) 0.0 else {
      val cgCount = seq.count(c => c == 'G' || c == 'C')
      cgCount.toDouble / length
    }
  }

  describe ("The DNA stabilityChecker") {
    it ("should return the correct ratio for a known sequence") {
      val sequence = "GATACCA-ATA"
      stabilityChecker(sequence) should be (0.2727272727 +- 1e-6)
    }

    // Create a generator to generate random strings of alleles from the sequence below
    // between 0 and 30 alleles in length, then use the generator to test the
    // stabilityChecker with a scalacheck forAll (replace ??? with your generator)
    import org.scalacheck.Gen

    val validAlleles = Seq('A','C','G','T','-')

    // put generator definition (and supporting functionality) here
    lazy val seqGen: Gen[String] = ???

    it ("should return a ratio between 0.0 and 1.0 for any genetic sequence") {
      forAll(seqGen) { sequence =>
        val stability = stabilityChecker(sequence)

        stability should (be >= 0.0 and be <= 1.0)
      }
    }
  }

}
