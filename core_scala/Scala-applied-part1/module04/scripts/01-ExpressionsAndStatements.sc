import java.io.{File, PrintWriter}
// an expression

val x = 1 + 2

// a statement

println(x)

// since everything has a return type

val un = println(x)


un == ()


class WriterOutput(writer: PrintWriter) {
  def write(s: String): Unit = writer.println(s)
}
var pathname1 = "D:\\Learn\\PythonProjects\\ml_lab\\core_scala\\Scala-applied-part1\\exercises-applied\\module04\\ex1.txt"

val ex1 = new PrintWriter(new File(pathname1))

val out1 = new WriterOutput(ex1)

out1.write("Hello")
out1.write("to")
out1.write("you")

ex1.close()


class WriterOutput2(writer: PrintWriter) {
  def write(s: String): WriterOutput2 = {
    writer.println(s)
    this
  }
}
pathname1 = "D:\\Learn\\PythonProjects\\ml_lab\\core_scala\\Scala-applied-part1\\exercises-applied\\module04\\ex2.txt"


val ex2 = new PrintWriter(new File(pathname1))

val out2 = new WriterOutput2(ex2)

out2.write("Hello").write("to").write("you")

ex2.close()
