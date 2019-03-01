
/* Copyright (C) 2010-2018 Escalate Software, LLC. All rights reserved. */

package koans
import org.scalatest.FunSuite
import org.scalatest.Matchers
import org.scalatest.SeveredStackTraces
import support.BlankValues._
import support.StopOnFirstFailure

class Module13Solutions extends FunSuite with Matchers with StopOnFirstFailure with SeveredStackTraces {

  // fun example drawn from real experience. From these sequences of DNA representations, we are going to
  // build up a list of sets containing the possible mutations at each position in the strings, like this:
  //
  // "GTAAGCTTAC"
  // "GACAGCT-AC"
  // "G-AACCTAAC"
  //
  // The process is to line up each index for the various sequences and
  // find the possibilities at that position. For example, at position 0
  // (the beginning character) only Gs can be found, while at the next
  // position, 1, there can be a T, A or -
  //
  // you should end up with the following list of sets (short hand notation used for brevity)
  //
  // Char position        Set
  // 0                    (G)
  // 1                    (T,A,-)
  // 2                    (A,C)
  // 3                    (A)
  // 4                    (G,C)
  // 5                    (C)
  // 6                    (T,-,A)
  // 7                    (A)
  // 8                    (C)
  // We will build this up step by step
  //

  val seq1 = "GTAAGCTTAC"
  val seq2 = "GACAGCT-AC"
  val seq3 = "G-AACCTAAC"
  val seq4 = "C-AACCTAAC"

  val listOfSeqs = List(seq1, seq2, seq3, seq4)

  // can you find an easy way to convert a String to a list of Chars?
  // if you need help converting a String to a list -
  // have a look at the completion options in the REPL for strings
  def stringToChars(s1: String): List[Char] = s1.toList

  test("Convert a string to a list of chars") {
    stringToChars(seq1) should be (List('G','T','A','A','G','C','T','T','A','C'))
  }

  // Next take this list of chars and convert it to a list of set of chars
  // with a single char in each, Set.apply or Set(ch) will put a char
  // into a set
  def stringToSetOfChars(s1: String): List[Set[Char]] = s1.toList.map { c => Set(c) }

  test("Convert a string to a list of set of chars (each set with one char in it)") {
    stringToSetOfChars(seq1).toString should be ("List(Set(G), Set(T), Set(A), Set(A), Set(G), Set(C), Set(T), Set(T), Set(A), Set(C))")
  }

  // Next we want to use the string to list of characters to obtain the alleles (letters) at each position
  // and insert it into the current set for that position. Sets will not duplicate an item already in the Set
  // so we know that if we keep adding alleles we will just get a list of the unique values that can be
  // at a given position in the sequence. You can build this up by:
  // - convert the string to a list of chars
  // - zip the chars with the list of sets representing the current possibilities at that position
  // - iterate over the resulting tuples and add the allele to the current set
  def combineZippedSetsAndString(s1: List[Set[Char]], seq: String): List[Set[Char]] = {
    for ((set, char) <- (s1 zip seq.toList))
      yield set + char
  }

  test("Combine list of sets with list of new chars") {
    val startSet = stringToSetOfChars(seq1)
    val nextSet = combineZippedSetsAndString(startSet, seq2)
    val finalSet = combineZippedSetsAndString(nextSet, seq3)
    finalSet.toString should be ("List(Set(G), Set(T, A, -), Set(A, C), Set(A), Set(G, C), Set(C), Set(T), Set(T, -, A), Set(A), Set(C))")
  }

  // let's put these together so that, given a list of sequence strings, we return a list of sets of chars with
  // every possible letter at that position, combining the stringToSetOfChars method for the head of the list
  // and the combineZippedSetsAndChars for the remaining elements of the list in turn. You can do this using the
  // foldLeft method demonstrated in the slides.
  //
  // sequences.head will give you the first item of the sequences list, and sequences.tail will give
  // you the rest to iterate over.
  def comboSetsForSequences(sequences: List[String]): List[Set[Char]] = {
    val head :: rest = sequences
    val startingSet = stringToSetOfChars(head)
    rest.foldLeft(startingSet){combineZippedSetsAndString(_, _)}
  }

  test("Find all combinations from sequences") {
    val allCombos = comboSetsForSequences(listOfSeqs)
    allCombos.toString should be ("List(Set(G, C), Set(T, A, -), Set(A, C), Set(A), Set(G, C), Set(C), Set(T), Set(T, -, A), Set(A), Set(C))")
  }


  // And now for something completely different
  val numbers = List(10, 3, 21, 7, 9, 13, 15, 10, 16, 2, 1, 8, 12)

  // first up, using what you know, write a method partitionByFirst that takes the first value of a list
  // of Ints, and partitions the rest of the list into two new lists, one with values lower than the first number,
  // and one with the rest. It should return three values, the first number, the list lower, and the remaining list
  // to satisfy the test below
  def partitionByFirst(numbers: List[Int]): (Int, List[Int], List[Int]) = {
    val head :: rest = numbers
    val (lower, remaining) = rest.partition(_ < head)
    (head, lower, remaining)
  }

  test("Partition by first") {
    val (head, lower, remaining) = partitionByFirst(numbers)
    head should be (10)
    lower should be (List(3, 7, 9, 2, 1, 8))
    remaining should be (List(21, 13, 15, 10, 16, 12))
  }

  // now, using pattern matching, write a method called mysteryFunction that will
  // detect the following cases with the following outcomes:
  // empty list (Nil) => Nil
  // List of Ints => split list into head, lower and remaining using the technique above, then using recursion,
  // return the result of mysteryFunction(lower) ::: List(head) ::: mysteryFunction(remaining)
  // What have you just created? Check that it works in the test below

  def mysteryFunction(numbers: List[Int]): List[Int] =
    numbers match {
      case Nil => Nil
      case l : List[Int] => {
          val head :: rest = l
          val (lower, remaining) = rest.partition(_ < head)
          mysteryFunction(lower) ::: List(head) ::: mysteryFunction(remaining)
      }
    }

  test("Can you tell what it is yet") {
    val newList = mysteryFunction(numbers)
    newList should be (List(1, 2, 3, 7, 8, 9, 10, 10, 12, 13, 15, 16, 21))
  }

  // Extra credit exercises:
  //
  // (The mystery function is a quicksort)
  //
  // Rename the mystery function and it's test with the real name for that algorithm, if you recognize it
  // The choice of the first number as pivot is not a good choice for a list that may already have some kind
  // of sort order, so if you have time, implement a new version that takes a number out of the center of the list
  // and uses that number to partition the rest of the list (without that chosen pivot). Write the function and a
  // suitable test to make sure it still works
  //
  // Going back to the previous example, where you used the foldLeft to create the combination of sequences, try
  // converting to the alternative notation of /: instead. Use the slides or the book for reference
}
