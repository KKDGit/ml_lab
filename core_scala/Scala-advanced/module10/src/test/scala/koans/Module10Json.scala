package koans

import koans.support.KoanSuite
import org.scalatest.{Matchers, SeveredStackTraces}
import play.api.libs.json._

class Module10Json extends KoanSuite with Matchers with SeveredStackTraces {

  trait Item {
    def title: String
  }

  case class Book(title: String, author: String, year: Int, pages: Int) extends Item

  case class Journal(title: String, issue: Int, month: Int, year: Int) extends Item

  case class DVD(title: String, director: String, year: Int, length: Int) extends Item

  case class CD(title: String, artist: String, year: Int, length: Int) extends Item

  case class Library(items: Seq[Item])

  // this is more tricky than it looks at first. Create a JSON format for a library and all of its parts
  // to serialize out and bring back in a library of items. Note that since Library holds on to Item
  // you will need to make Item, not just the individual case classes, serializable to/from JSON
  // which means differentiating them. Fortunately, they have different fields in each case so you
  // can likely find a way to use that information to tell them apart, or come up with a more cunning
  // way...

  val library = Library(Seq(
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

  test ("It should serialize a library full of items out to a file, and serialize it back in again") {
    // uncomment below to test your serializers
    /*
    val jsonLibraryString = Json.prettyPrint(Json.toJson(library))

    // if you're feeling brave, uncomment this (but the strings will need to match exactly!)
    jsonLibraryString should be (compareString)

    Json.parse(jsonLibraryString).as[Library] should be (library)
    */
  }

  val compareString =
    """{
      |  "items" : [ {
      |    "title" : "Catcher in the Rye",
      |    "author" : "J.D. Salinger",
      |    "year" : 1951,
      |    "pages" : 224
      |  }, {
      |    "title" : "National Geographic",
      |    "issue" : 170,
      |    "month" : 12,
      |    "year" : 2005
      |  }, {
      |    "title" : "This is Spinal Tap",
      |    "director" : "Rob Reiner",
      |    "year" : 1984,
      |    "length" : 82
      |  }, {
      |    "title" : "Live Fast, Die Fast",
      |    "artist" : "Wolfsbane",
      |    "year" : 1989,
      |    "length" : 35
      |  }, {
      |    "title" : "Daemon",
      |    "author" : "Daniel Suarez",
      |    "year" : 2009,
      |    "pages" : 448
      |  }, {
      |    "title" : "American Motorcyclist",
      |    "issue" : 58,
      |    "month" : 12,
      |    "year" : 2010
      |  }, {
      |    "title" : "The Mummy",
      |    "director" : "Stephen Sommers",
      |    "year" : 1999,
      |    "length" : 125
      |  }, {
      |    "title" : "Masterplan",
      |    "artist" : "Oasis",
      |    "year" : 1998,
      |    "length" : 68
      |  }, {
      |    "title" : "Lila",
      |    "author" : "Robert M. Pirsig",
      |    "year" : 1992,
      |    "pages" : 480
      |  }, {
      |    "title" : "Linux Format",
      |    "issue" : 200,
      |    "month" : 11,
      |    "year" : 2010
      |  }, {
      |    "title" : "Coraline",
      |    "director" : "Henry Selick",
      |    "year" : 2009,
      |    "length" : 96
      |  }, {
      |    "title" : "Alright, Still",
      |    "artist" : "Lily Allen",
      |    "year" : 2007,
      |    "length" : 46
      |  } ]
      |}""".stripMargin
}
