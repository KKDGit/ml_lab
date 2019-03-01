sealed trait StatementLine

case class Print(printList: Seq[String]) extends StatementLine

case class For(variable: String, start: Int, end: Int, by: Option[Int])
  extends StatementLine

case class Goto(lineNumber: Int) extends StatementLine

case object Next extends StatementLine


val basicProgram: Seq[StatementLine] = Seq(
  For("x", 1, 10, Some(1)),
  Print(Seq("Hello, world")),
  Print(Seq("Another line")),
  Next
)

basicProgram.collect {
  case Print(items) => items
}

sealed trait LinkedList[T] {
  def isEmpty: Boolean
  def head: T
  def tail: LinkedList[T]
}

case class Empty[T]() extends LinkedList[T] {
  def isEmpty: Boolean = true
  def head: T = throw new IllegalStateException("head of empty list")
  def tail: LinkedList[T] =
    throw new IllegalStateException("tail of empty list")
}

case class Cons[T](head: T, tail: LinkedList[T]) extends LinkedList[T] {
  def isEmpty: Boolean = false
}

val myLinkedList = Cons(1, Cons(2, Empty[Int]()))

myLinkedList.head
myLinkedList.tail.head
