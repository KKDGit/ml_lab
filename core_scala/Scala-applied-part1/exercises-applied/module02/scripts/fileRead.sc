import scala.io.Source

for(line <-
      Source.fromFile(
        "D:\\Learn\\PythonProjects\\ml_lab\\core_scala\\Scala-applied-part1\\exercises-applied\\module02\\inputFile.txt")
        .getLines()) {
  println(line)
}