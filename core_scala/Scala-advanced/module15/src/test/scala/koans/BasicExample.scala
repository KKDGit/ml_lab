package koans

import org.scalatest.{FunSuite, SeveredStackTraces, Matchers}

import scala.util.parsing.combinator.JavaTokenParsers

/**
 * Created by dick on 12/2/14.
 */
class BasicExample extends FunSuite with Matchers with SeveredStackTraces {

  // example grammar for very (very) simple BASIC
  //  lineNumber ::= number.
  //  int ::= number.
  //  variable ::= ident.
  //  printList ::= stringLiteral { "," stringLiteral }.
  //  print ::= "PRINT" printList.
  //  for ::= "FOR" ident "=" int "TO" int [ "BY" int ].
  //  next ::= "NEXT".
  //  goto ::= "GOTO" lineNumber.
  //  validStatement ::= print | for | next | goto.
  //  validLine ::= lineNumber validStatement.

  case class Variable(name: String)
  case class LineNumber(line: Int)

  sealed trait StatementLine
  case class Print(printList: Seq[String]) extends StatementLine
  case class For(variable: Variable, start: Int, end: Int, by: Option[Int]) extends StatementLine
  case class Goto(lineNumber: LineNumber) extends StatementLine
  case object Next extends StatementLine

  case class CompleteLine(line: LineNumber, statement: StatementLine)

  object BasicParser extends JavaTokenParsers {
    def gotos:  Parser[String] = "GOTO"
    def fors:   Parser[String] = "FOR"
    def tos:    Parser[String] = "TO"
    def bys:    Parser[String] = "BY"
    def nexts:  Parser[String] = "NEXT"
    def prints: Parser[String] = "PRINT"

    def lineNumber: Parser[LineNumber] = """\d+""".r ^^ { ln => LineNumber(ln.toInt) }
    def int: Parser[Int] = wholeNumber ^^ { n => n.toInt }

    def variable: Parser[Variable] = ident ^^ { v => Variable(v) }
    def printList: Parser[Seq[String]] = repsep(stringLiteral, ",")

    def printStatement: Parser[Print] = prints ~> printList ^^ { pl => Print(pl) }

    def gotoStatement: Parser[Goto] = gotos ~> lineNumber ^^ Goto
    def nextStatement: Parser[Next.type] = nexts ^^ { next => Next }

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

  test ("Parses a simple NEXT statement on a line") {
    BasicParser("40 NEXT") should be
      (CompleteLine(LineNumber(40), Next))
  }

  test ("Parses a GOTO statement with a line number after it") {
    BasicParser("60 GOTO 10") should be
      (CompleteLine(LineNumber(60), Goto(LineNumber(10))))
  }

  test ("Parses a PRINT statement with no items") {
    BasicParser("70 PRINT") should be
      (CompleteLine(LineNumber(70), Print(Seq.empty[String])))
  }

  test ("Parses a PRINT statement with 1 item") {
    BasicParser("10 PRINT \"hello world\"") should be
      (CompleteLine(LineNumber(10), Print(Seq("\"hello world\""))))
  }

  test ("Parses a PRINT statement with 2 items") {
    BasicParser("20 PRINT \"item1\", \"item2\"") should be
      (CompleteLine(LineNumber(20), Print(Seq("\"item1\"", "\"item2\""))))
  }

  test ("Parses a FOR loop without a BY") {
    BasicParser("30 FOR x = 1 TO 20") should be
      (CompleteLine(LineNumber(30), For(Variable("x"), 1, 20, None)))
  }

  test ("Parses a FOR loop with a BY") {
    BasicParser("30 FOR x = 0 TO 20 BY 2") should be
      (CompleteLine(LineNumber(30), For(Variable("x"), 0, 20, Some(2))))
  }
}
