import scala.util.Try

case class Person(first: String, last: String, age: Int)

val p1 = Person("Fred", "Frederickson", 28)

p1 match {
  case Person("Fred", last, 28) => "Fred is 28"
  case _ => "Something Else"
}

Person.unapply(p1)

val xs = List(1,2,3,4)

xs match {
  case head :: tail =>
    println(head)
    println(tail)
  case _ => println("something else")
}

List.unapplySeq(xs)

val coordsStr = "-121.432, 34.002"

object Coords {
  def unapply(coordsStr: String): Option[(Double, Double)] = Try {
    val fields = coordsStr.split(",").map(_.trim.toDouble)
    (fields(0), fields(1))
  }.toOption
}

object CoordSeq {
  def unapplySeq(coordsStr: String): Option[Seq[Double]] = Try {
    coordsStr.split(",").toList.map(_.trim.toDouble)
  }.toOption
}

def matchCoords1(coordsStr: String): Unit = coordsStr match {
  case Coords(x, y) =>
    println(s"x = $x")
    println(s"y = $y")
}

def matchCoords2(coordsStr: String): Unit = coordsStr match {
case CoordSeq(c @ _*) =>
c foreach println
}

def matchCoords3(coordsStr: String): Unit = coordsStr match {
  case CoordSeq(x, y, _*) =>
    println(x)
    println(y)
}

matchCoords1("-121.432, 34.002")
matchCoords2("-121.432, 34.002")
matchCoords3("-121.432, 34.002")

//matchCoords1("KranthiKumar")
//scala.MatchError: -121.432 34.002 (of class java.lang.String)
//matchCoords2("KranthiKumar")
//scala.MatchError: -121.432 34.002 (of class java.lang.String)
//matchCoords3("Kranthi,Kumar")
//scala.MatchError: -121.432 34.002 (of class java.lang.String)
