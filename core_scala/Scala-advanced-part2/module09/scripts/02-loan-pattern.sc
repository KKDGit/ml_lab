import java.io._
import scala.io.Source

// might need to change this choice on windows:
val bashFile = new File(".bashrc")

def withFileLines[A](file: File)(fn: Seq[String] => A): A = {
  val source = Source.fromFile(file)
  try {
    fn(source.getLines().toList)
  } finally source.close()
}

val matches = withFileLines(bashFile) { lines =>
  for (line <- lines if line.contains("alias "))
    yield line.split("alias ").last
}

matches foreach println

import resource._

val matches2 = for {
  source <- managed(Source.fromFile(bashFile))
} yield {
  val lines = source.getLines().toList
  for (line <- lines if line.contains("alias "))
    yield line.split("alias ").last
}
