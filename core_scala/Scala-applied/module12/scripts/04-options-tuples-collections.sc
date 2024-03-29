import scala.collection.{Iterable, Set}

def matchOption(o: Option[Int]) =  o match {
  case Some(n) if n > 10 => "It's a number above 10"
  case Some(_)           => "It's a number 10 or less"
  case None              => "No number given"
}

matchOption(Some(50))
matchOption(Some(5))
matchOption(None)

val o1 = Some(10)
val o2: Option[Int] = None

o1.getOrElse(0)
o2.getOrElse(0)

def matchTuple3(tup: (Int, Boolean, String)): String = tup match {
  case (1, flag, string) => s"a 1 followed by $flag and $string"
  case (i, true, "Fred") => s"a true Fred with int $i"
  case (a, b, c)         => s"Some other tuple int $a, " +
    s"flag $b, string $c"
}


matchTuple3((1, false, "Sally"))
matchTuple3((1, true, "Harry"))
matchTuple3((2, true, "Fred"))
matchTuple3((1, true, "Fred"))
matchTuple3((2, false, "Fred"))


def matchList(xs: List[Int]): String = xs match {
  case 1 :: 2 :: rest => s"A 1, 2 list followed by $rest"
  case a :: b :: _ => s"A list of at least 2 items, " +
    s"starting with $a, $b"
  case a :: Nil => s"A single element list of $a"
  case Nil => "The empty list"
}

matchList(List(1,2,3))
matchList(List(1,2))
matchList(List(1,3,4))
matchList(List(4))
matchList(Nil)


def matchSeq(xs: Vector[Int]): String = xs match {
  case 1 +: 2 +: rest => s"A 1, 2 vector followed by $rest"
  case Vector(a, b, rest @ _*) => s"A vector of at least 2 items, " +
    s"starting with $a, $b, rest is $rest"
  case Vector(a) => s"A single element vector of $a"
  case Vector() => "The empty vector"
}

matchSeq(Vector(1,2,3))
matchSeq(Vector(1,2))
matchSeq(Vector(1,3,4))
matchSeq(Vector(4))
matchSeq(Vector.empty)
matchSeq(Vector())


import scala.util._

def matchTry(t: Try[_]): String = t match {
  case Success(x) => s"It worked, result is $x"
  case Failure(e) => s"It failed with $e"
}

matchTry(Try(4/2))
matchTry(Try(4/0))


def match1(xs: Seq[Int]): String = xs match {
    case Seq(1, 2, rest) => s"A 1, 2 collection followed by $rest"
    case Seq(a, b, rest @ _*) => s"A collection of at least " +
      s"2 items, starting with $a, $b, rest is $rest"
    case Seq(a) => s"A single element collection of $a"
    case Seq() => "The empty collection"
}

match1(List(1,2,3))
match1(List(1,2))
match1(List(1,2,3,4,5))
match1(List(1,3,4))
match1(List(1,3,4,5,6))
match1(List(4))
match1(Nil)

match1(Vector(1,2,3))
match1(Vector(1,2))
match1(Vector(1,3,4))
match1(Vector(4))
match1(Vector.empty)