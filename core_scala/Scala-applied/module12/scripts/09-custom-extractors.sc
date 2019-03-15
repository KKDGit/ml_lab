import scala.util.Try

case class Person(first: String, last: String, age: Int)

val p1 = Person("Fred", "Frederickson", 28)

p1 match {
  case Person("Fred", last, 28) => "Fred is 28"
}

Person.unapply(p1)

val xs = List(1,2,3,4)

xs match {
  case head :: tail =>
    println(head)
    println(tail)
}

List.unapplySeq(xs)

val coordsStr = "-121.432, 34.002"

object Coords {
  def unapply(coordsStr: String): Option[(Double, Double)] = Try {
    val fields = coordsStr.split(",").map(_.trim.toDouble)
    (fields(0), fields(1))
  }.toOption
}

coordsStr match {
  case Coords(x, y) =>
    println(s"x = $x")
    println(s"y = $y")
}

object CoordSeq {
  def unapplySeq(coordsStr: String): Option[Seq[Double]] = Try {
    coordsStr.split(",").toList.map(_.trim.toDouble)
  }.toOption
}

coordsStr match {
  case CoordSeq(c @ _*) =>
    c foreach println
}

coordsStr match {
  case CoordSeq(x, y, _*) =>
    println(x)
    println(y)
}

