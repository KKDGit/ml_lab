
/* Copyright (C) 2010-2018 Escalate Software, LLC. All rights reserved. */

package koans
import java.io.File

import org.scalatest.Matchers
import support.KoanSuite

import xml.Node
import org.scalatest.SeveredStackTraces

class Module10XMLSolutions extends KoanSuite with Matchers with SeveredStackTraces {

  trait LibraryItem {
    val title: String
    val year: Int

    def toXML: Node
  }

  case class Book(title: String, author: String, year: Int, pages: Int) extends LibraryItem {
    def toXML: Node = <book>
      <title>{title}</title>
      <author>{author}</author>
      <year>{year}</year>
      <pages>{pages}</pages>
    </book>
  }

  object Book {
    def fromXML(node: Node) =
      Book((node \ "title").text, (node \ "author").text, (node \ "year").text.toInt, (node \ "pages").text.toInt)
  }

  case class Journal(title: String, issue: Int, month: Int, year: Int) extends LibraryItem {
    def toXML: Node = <journal>
      <title>{title}</title>
      <issue>{issue}</issue>
      <month>{month}</month>
      <year>{year}</year>
    </journal>
  }

  object Journal {
    def fromXML(node: Node) =
      Journal((node \ "title").text, (node \ "issue").text.toInt, (node \ "month").text.toInt, (node \ "year").text.toInt)
  }

  case class DVD(title: String, director: String, year: Int, length: Int) extends LibraryItem {
    def toXML: Node = <dvd>
      <title>{title}</title>
      <director>{director}</director>
      <year>{year}</year>
      <length>{length}</length>
    </dvd>
  }

  object DVD {
    def fromXML(node: Node) =
      DVD((node \ "title").text, (node \ "director").text, (node \ "year").text.toInt, (node \ "length").text.toInt)
  }

  case class CD(title: String, artist: String, year: Int, length: Int) extends LibraryItem {
    def toXML: Node = <cd>
      <title>{title}</title>
      <artist>{artist}</artist>
      <year>{year}</year>
      <length>{length}</length>
    </cd>
  }

  object CD {
    def fromXML(node: Node) =
      CD((node \ "title").text, (node \ "artist").text, (node \ "year").text.toInt, (node \ "length").text.toInt)
  }

  koan("toXML methods on each of the case classes") {
    // fill out the toXML methods in the case classes above so that the following tests work
    // note - the tests use an asFlatString function to remove formatting, so make your toXML methods
    // look nice in the source code and don't worry about the string formatting used in the tests.

    val catcherBook = Book("Catcher in the Rye", "J.D. Salinger", 1951, 224).toXML

    asFlatString(catcherBook) should be ("<book><title>Catcher in the Rye</title><author>J.D. Salinger</author><year>1951</year>" +
       "<pages>224</pages></book>")

    val nationalGeographic = Journal("National Geographic", 170, 12, 2005).toXML

    asFlatString(nationalGeographic) should be ("<journal><title>National Geographic</title><issue>170</issue><month>12</month><year>2005</year></journal>")

    val spinalTap = DVD("This is Spinal Tap", "Rob Reiner", 1984, 82).toXML
    
    asFlatString(spinalTap) should be ("<dvd><title>This is Spinal Tap</title><director>Rob Reiner</director><year>1984</year><length>82</length></dvd>")

    val wolfsbaneLfdf = CD("Live Fast, Die Fast", "Wolfsbane", 1989, 35).toXML

    asFlatString(wolfsbaneLfdf) should be ("<cd><title>Live Fast, Die Fast</title><artist>Wolfsbane</artist><year>1989</year><length>35</length></cd>")
  }

  koan("fromXML in each of the companion objects") {
    // now fill out the fromXML in the companion objects so that the following work
    val catcherBook = Book.fromXML(<book><title>Catcher in the Rye</title><author>J.D. Salinger</author><year>1951</year><pages>224</pages></book>)

    catcherBook should be (Book("Catcher in the Rye", "J.D. Salinger", 1951, 224))

    val nationalGeographic = Journal.fromXML(<journal><title>National Geographic</title><issue>170</issue><month>12</month><year>2005</year></journal>)

    nationalGeographic should be (Journal("National Geographic", 170, 12, 2005))

    val spinalTap = DVD.fromXML(<dvd><title>This is Spinal Tap</title><director>Rob Reiner</director><year>1984</year><length>82</length></dvd>)

    spinalTap should be (DVD("This is Spinal Tap", "Rob Reiner", 1984, 82))

    val wolfsbaneLfdf = CD.fromXML(<cd><title>Live Fast, Die Fast</title><artist>Wolfsbane</artist><year>1989</year><length>35</length></cd>)

    wolfsbaneLfdf should be (CD("Live Fast, Die Fast", "Wolfsbane", 1989, 35))
  }

  koan("Attribute access") {
    // complete the following function to pull the price out of each of the child nodes under a given node, and add up
    // the total of the price attributes

    def totalPrices(node: Node): Double = {
      (node \\ "@price").map(_.text.toDouble).sum
    }

    val cart = <cart>
      <item name="Microphone" price="145.95"/>
      <item name="Mixing Board" price="299.95"/>
      <item name="XLR M to XLR M cable" price="24.95"/>
    </cart>

    totalPrices(cart) should be (470.85 +- (0.001))
  }

  koan("Parsing the XML file") {
    // In the test resources is a file called LibraryItems.xml - take a look at it if you like.
    // given your current fromXML functions, read in the file and use a for loop with pattern matching to
    // create a list of LibraryItem with the various different items in it by filling out the function
    // stub below
    //
    // You can use
    //   val file = new File(this.getClass.getClassLoader.getResource(fileName).toURI)
    // To find the file in the resources ready to use it

    def readInLibraryItems(fileName: String): Seq[LibraryItem] = {
      val file = new File(this.getClass.getClassLoader.getResource(fileName).toURI)
      val library = scala.xml.XML.loadFile(file)

      library match {
        case <library>{libraryItems @ _*}</library> =>
          val items = for (item <- libraryItems) yield item match {
            case book @ <book>{_*}</book> => Some(Book.fromXML(book.head))
            case journal @ <journal>{_*}</journal> => Some(Journal.fromXML(journal.head))
            case dvd @ <dvd>{_*}</dvd> => Some(DVD.fromXML(dvd.head))
            case cd @ <cd>{_*}</cd> => Some(CD.fromXML(cd.head))
            case _ => None  // ignore any whitespace nodes that don't match the above
          }
          items.flatten   // filter out the Nones from the list
        case _ => Nil // if not a library - just return Nil
      }
    }

    val items = readInLibraryItems("LibraryItems.xml")

    items should be (List(
      Book("Catcher in the Rye", "J.D. Salinger", 1951, 224),
      Journal("National Geographic", 170, 12, 2005),
      DVD("This is Spinal Tap", "Rob Reiner", 1984, 82),
      CD("Live Fast, Die Fast", "Wolfsbane", 1989, 35),
      Book("Daemon", "Daniel Suarez", 2009, 448),
      Journal("American Motorcyclist", 58, 12, 2010),
      DVD("The Mummy", "Stephen Sommers", 1999, 125),
      CD("Masterplan", "Oasis", 1998, 68),
      Book("Lila", "Robert M. Pirsig", 1992, 480),
      Journal("Linux Format", 200, 11, 2010),
      DVD("Coraline", "Henry Selick", 2009, 96),
      CD("Alright, Still", "Lily Allen", 2007, 46)
    ))
    
  }
  
  // the following allows us to compare XML nodes easily by getting rid of all whitespace and formatting
  // so we can do a string compare
  def asFlatString(node: scala.xml.Node): String =
    node.toString.split("\n").map(_.trim).mkString
}
