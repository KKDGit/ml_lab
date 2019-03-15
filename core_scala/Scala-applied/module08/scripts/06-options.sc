val s1: String = "hello"
val s2: String = null

s1.length
// will compile but throws an exception
// s2.length

val os1: Option[String] = Some("hello")
val os2: Option[String] = None

// will no longer compile
// os1.length

os1.map(_.length)
os2.map(_.length)

val numWords = Map(1 -> "one", 2 -> "two", 3 -> "three")
numWords(1)
// numWords(4)

val word1 = numWords.get(1)
val word2 = numWords.get(4)

word1 match {
  case Some(word) => word
  case None => "unknown"
}

word2.getOrElse("unknown")

def fourthLetter(i: Int): Option[Char] =
  for {
    word <- numWords.get(i)
    char <- word.drop(4).headOption
  } yield char

fourthLetter(1)
fourthLetter(3)


def fourthLetters(nums: Seq[Int]): Seq[Char] =
  for {
    i <- nums
    word <- numWords.get(i).toSeq
    char <- word.drop(4).headOption.toSeq
  } yield char

fourthLetters(List(1, 2, 3))

