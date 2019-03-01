import java.io.File

import scala.io.Source

def times(n: Int)(fn: => Unit): Unit = {
  for (_ <- 1 to n) fn
}

times(5) {
  println("hello")
}

def withLinesFromFile[A](file: File)(fn: Seq[String] => A): A = {
  val src = Source.fromFile(file)
  try {
    fn(src.getLines().toSeq)
  } finally src.close()
}


withLinesFromFile(new File("./dev/Scala/Training/scala-advanced-training/exercises-advanced/module08/scripts/09-curried-functions.sc")) {
  lines => lines.length
}
