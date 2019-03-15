import java.io.File

val xmlFile = new File(
  getClass.getClassLoader.getResource("LibraryItems.xml").toURI
)

import scala.xml.XML

val books = XML.loadFile(xmlFile)

books \ "book"

books \ "title"

books \\ "title"

for (title <- books \\ "title") {
  println(title.text)
}

val xmlFileAttr = new File(
  getClass.getClassLoader.getResource("LibraryItemsAttr.xml").toURI
)

val booksAttrs = XML.loadFile(xmlFileAttr)

booksAttrs \\ "item"

for (b <- booksAttrs \\ "item") {
  println((b \ "@type").text + ": " + (b \ "@title").text)
}


case class Book(title: String, author: String, year: Int, pages: Int)


def bookToXML(book: Book) = {
  import book._
  <book>
    <title>{title}</title>
    <author>{author}</author>
    <year>{year}</year>
    <pages>{pages}</pages>
  </book>
}

val name = "hello"
val x = 10

<item name={name} x={x.toString}></item>


def bookToXMLAttr(book: Book) = {
  import book._
  <item type="book" title={title} author={author}
        year={year.toString} pages={pages.toString}></item>
}

val bookList = Seq(
  Book("Catcher in the Rye", "J.D. Salinger", 1951, 224),
  Book("I Ah to Thai", "E.R. Das", 1992, 10)
)

println(<library>{bookList.map(bookToXML)}</library>)

println(<library>{bookList.map(bookToXMLAttr)}</library>)


def xmlToBook(xml: scala.xml.Elem): Book = {
  val title = (xml \ "title").text
  val author = (xml \ "author").text
  val year = (xml \ "year").text.toInt
  val pages = (xml \ "pages").text.toInt
  Book(title, author, year, pages)
}

val bookXml = <book>
    <title>I ah to Thai</title>
    <author>E.R. Das</author>
    <year>1992</year>
    <pages>10</pages>
  </book>

xmlToBook(bookXml)

bookXml match {
  case <book>{contents @ _*}</book> =>
    println(s"""It's a book: ${contents.text}""")
  case _ =>
    println("Not a book")
}


val library = <library>
  <book>
    <title>Catcher in the Rye</title>
    <author>J.D. Salinger</author>
    <year>1951</year>
    <pages>224</pages>
  </book>
  <journal>
    <title>National Geographic</title>
    <issue>170</issue>
    <month>12</month>
    <year>2005</year>
  </journal>
  <dvd>
    <title>This is Spinal Tap</title>
    <director>Rob Reiner</director>
    <year>1984</year>
    <length>82</length>
  </dvd>
</library>


val xs = List(1,2,3)

def printAll(items: Int*) = {
  items foreach println
}

printAll(xs: _*)

xs match {
  case List(head, tail @ _*) => println(head)
    println(tail)
}