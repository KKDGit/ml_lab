import scala.util.parsing.combinator._

object BasicParser extends JavaTokenParsers {
  def gotos: Parser[String] = "GOTO"
  def fors: Parser[String] = "FOR"
  def tos: Parser[String] = "TO"
  def bys: Parser[String] = "BY"
  def nexts: Parser[String] = "NEXT"
  def prints: Parser[String] = "PRINT"

  def int: Parser[Int] = wholeNumber ^^ { n => n.toInt }

  case class LineNumber(line: Int)
  def lineNumber: Parser[LineNumber] =
    """\d+""".r ^^ { ln => LineNumber(ln.toInt) }

  case class Variable(name: String)
  def variable: Parser[Variable] = ident ^^ { v => Variable(v) }

  // statement lines
  sealed trait StatementLine

  case class Print(printList: Seq[String]) extends StatementLine

  case class For(variable: Variable, start: Int, end: Int, by: Option[Int])
    extends StatementLine

  case class Goto(lineNumber: LineNumber) extends StatementLine

  case object Next extends StatementLine

  case class CompleteLine(line: LineNumber, statement: StatementLine)


  def nextStatement: Parser[Next.type] =
    nexts ^^ { next => Next } // param ignored

  def gotoStatement: Parser[Goto] =
    gotos ~ lineNumber ^^ {
      case g ~ ln => Goto(ln)
    }

  def printList: Parser[Seq[String]] =
    repsep(stringLiteral, ",")

  def printStatement: Parser[Print] =
    prints ~> printList ^^ { pl => Print(pl) }


  def byPart: Parser[Int] = bys ~> int

  def forStatement: Parser[For] =
    fors ~ variable ~ "=" ~ int ~ tos ~ int ~ opt(byPart) ^^ {
      case _ ~ vname ~ _ ~ froml ~ _ ~ tol ~ optBy =>
        For(vname, froml, tol, optBy)
    }


  def completeLine: Parser[CompleteLine] =
    lineNumber ~
      (printStatement | gotoStatement | nextStatement | forStatement) ^^ {
      case lineNumber ~ statement => CompleteLine(lineNumber, statement)
    }

  def apply(input: String): CompleteLine =
    parseAll(completeLine, input) match {
      case Success(result, _) => result
      case failure : NoSuccess => scala.sys.error(failure.msg)
    }

}


BasicParser("40 NEXT")
BasicParser("60 GOTO 10")
BasicParser("70 PRINT")
BasicParser("10 PRINT \"hello world\"")
BasicParser("20 PRINT \"item1\", \"item2\"")
BasicParser("30 FOR x = 1 TO 20")
BasicParser("30 FOR x = 0 TO 20 BY 2")

BasicParser("10 PRINT \"hello\" \"No sep\"")
