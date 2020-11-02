
/* Copyright (C) 2010-2019 Escalate Software, LLC. All rights reserved. */

package koans

import org.scalatest.Matchers
import scala.util.Try
import org.scalatest.{SeveredStackTraces, FunSuite}
import koans.support.{KoanSuite, StopOnFirstFailure}

import scala.util.parsing.combinator.JavaTokenParsers

class Module15 extends FunSuite with Matchers with SeveredStackTraces {

  // definitions from the Expr case class examples from Flight 12
  sealed abstract class Expr
  case class Var(name: String) extends Expr
  case class Number(num: Double) extends Expr
  case class UnOp(operator: String, arg: Expr) extends Expr
  case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

  // in the following parser, there are only a few definitions currently, and they are
  // fixed to constant results at present. In order to get the parser to work, you will
  // have to replace the default values with real tokens and parsers. Keep running the
  // tests as you go to refine the grammar, and eventually you should get to see all
  // greens. The parseXXX fields at the end are to help you test the parsers as you go.
  object ExprParser extends JavaTokenParsers {

    // 1a - first add the string literal tokens for our supported unops: "-", "abs", "sin" and "cos"

    // 1b - now make a validunop parser that will take any of the unops defined above
    // this current definition is just to keep things compiling, replace it
    def validunop: Parser[String] = ""

    // 2a - next add the string literal tokens for our supported binops: "+", "*", "/", "min", "max"
    // note, you can use the same "-" definition from above in binops too, so no need to add
    // that again

    // 2b - and add the validbinop parser that takes any of the binops defined above (and also "-")
    // this current definition is just to keep things compiling, replace it
    def validbinop: Parser[String] = ""

    // 3 -add a Parser[Number] for any floating point number and convert it to the Number case class
    // this definition is currently compilable nonsense - replace it
    def number: Parser[Number] = "nonsense" ^^ { _ => Number(0) }

    // 4 - and a Parser[Var] for any valid ident and convert it to the Var case class
    // this definition is currently compilable nonsense - replace it
    def varnm: Parser[Var] = "nonsense" ^^ { _ => Var("nonsense") }

    // the rest will have to be done in order, refining expr (at the bottom) as you go because
    // they forward reference expr, and it is currently not usefully defined.

    // 5 - define a Parser[UnOp] that combines validunop and an expr, and returns a new UnOp case class
    // this definition is currently compilable nonsense - replace it
    def unop: Parser[UnOp] = "nonsense" ^^ { _ => UnOp("-", Number(0)) }

    // I'll give you these ones, since they are pretty easy
    def openParen: Parser[String] = "("
    def closeParen: Parser[String] = ")"

    // 6 - now use the above definitions (and expr which will be filled in below) to
    // create a Parser[BinOp] that starts with an opening paren, then expr, then
    // valid bin op, then another expr and finally a closing paren. For simplicity
    // we are going to assume that binops are always enclosed in parens, but if you
    // want to go further, try making the parens optional some time (finish the
    // rest of the exercises first though)

    // this definition is currently compilable nonsense - replace it
    def binop: Parser[BinOp] = "nonsense" ^^ { _ => BinOp("-", Number(0), Number(0)) }


    // 7 - Make a Parser[Expr]s that can handle naked expressions (any
    // unop, number, binop or var)


    // 8 - Now make another Parser[Expr] that can handle any expression (as
    // defined above) that has a set of parens around it.


    // 9 - finally make a Parser[Expr] that can take either the naked expr
    // (no parens) or the paren enclosed expression
    // Note that you will probably need to keep refining expr as you run your
    // tests because other parsers reference it, but in the end it should
    // be defined only in terms of naked expressions and paren expressions.

    // this definition is currently compilable nonsense - replace it
    def expr: Parser[Expr] = "nonsense" ^^ { _ => Number(0) }

    def apply(input: String): Expr = parseAll(expr, input) match {
      case Success(result, _) => result
      case failure : NoSuccess => scala.sys.error(failure.msg)
    }

    // following only used as a testing convenience - should use apply method
    // above for real expression parsing
    def checkParse(parser: Parser[Any], parse: String, compare: Any) {
      parseAll(parser, parse) match {
        case Success(result, _) => result should be (compare)
        case _ => fail(s"Failed to parse string $parse")
      }
    }
  }


  // first complete steps 1a and 1b above and make sure this test passes
  test ("validunop should parse -, abs, sin, cos") {
    ExprParser.checkParse(ExprParser.validunop, "-", "-")
    ExprParser.checkParse(ExprParser.validunop, "abs", "abs")
    ExprParser.checkParse(ExprParser.validunop, "sin", "sin")
    ExprParser.checkParse(ExprParser.validunop, "cos", "cos")
  }

  // next complete steps 2a and 2b above to get this to pass
  test ("validbinop should parse +, -, *, /, min, max") {
    ExprParser.checkParse(ExprParser.validbinop, "+", "+")
    ExprParser.checkParse(ExprParser.validbinop, "-", "-")
    ExprParser.checkParse(ExprParser.validbinop, "*", "*")
    ExprParser.checkParse(ExprParser.validbinop, "/", "/")
    ExprParser.checkParse(ExprParser.validbinop, "min", "min")
    ExprParser.checkParse(ExprParser.validbinop, "max", "max")
  }

  // complete step 3 and get this to pass
  test ("number should parse valid numbers into Number case class instances") {
    ExprParser.checkParse(ExprParser.number, "5", Number(5.0))
    ExprParser.checkParse(ExprParser.number, "5.67", Number(5.67))
    ExprParser.checkParse(ExprParser.number, "-5.67", Number(-5.67))
  }

  test ("varnm should parse valid identifiers into Var case class instances") {
    ExprParser.checkParse(ExprParser.varnm, "x", Var("x"))
    ExprParser.checkParse(ExprParser.varnm, "fred", Var("fred"))
    ExprParser.checkParse(ExprParser.varnm, "a123", Var("a123"))
    ExprParser.checkParse(ExprParser.varnm, "do_or_die", Var("do_or_die"))
  }

  // now that you have got the hang of it, start working on the remaining steps
  // above to refine your parser and get all of these remaining tests to pass
  // note that as you change the parser, different errors may turn red/green
  // so make sure you check that you are working on the right problem (i.e.
  // you are looking at the correct failing test.)

  test ("should parse a simple integer") {
    ExprParser("5") should be (Number(5.0))
  }

  test ("should parse a simple decimal") {
    ExprParser("5.67") should be (Number(5.67))
  }

  test ("should parse a single char simple variable") {
    ExprParser("x") should be (Var("x"))
  }

  test ("should parse a multi char simple variable") {
    ExprParser("xyzzy") should be (Var("xyzzy"))
  }

  test ("shouldn't care about parens and/or single spaces around numbers") {
    ExprParser("(5)") should be (Number(5.0))
    ExprParser("(5.67)") should be (Number(5.67))
    ExprParser(" 5 ") should be (Number(5.0))
    ExprParser(" 5.67 ") should be (Number(5.67))
    ExprParser("( 5 )") should be (Number(5.0))
    ExprParser("( 5.67 )") should be (Number(5.67))
  }

  test ("shouldn't care about parens and/or single spaces around variables") {
    ExprParser("(x)") should be (Var("x"))
    ExprParser("(xyzzy)") should be (Var("xyzzy"))
    ExprParser(" x ") should be (Var("x"))
    ExprParser(" xyzzy ") should be (Var("xyzzy"))
    ExprParser("( x )") should be (Var("x"))
    ExprParser("( xyzzy )") should be (Var("xyzzy"))
  }

  test ("shouldn't allow unmatched parens") {
    Try(ExprParser("5.67)")).isFailure should be (true)
    Try(ExprParser("(5.67")).isFailure should be (true)
    Try(ExprParser("x)")).isFailure should be (true)
    Try(ExprParser("(x")).isFailure should be (true)
    Try(ExprParser("xyzzy)")).isFailure should be (true)
    Try(ExprParser("(xyzzy")).isFailure should be (true)
    Try(ExprParser("xyzzy )")).isFailure should be (true)
    Try(ExprParser("( xyzzy")).isFailure should be (true)
  }

  test ("should parse a symbolic unary operator") {
    ExprParser("-6.78") should be (UnOp("-", Number(6.78)))
    ExprParser("- 6.78") should be (UnOp("-", Number(6.78)))
    ExprParser("-(6.78)") should be (UnOp("-", Number(6.78)))
    ExprParser("- (6.78)") should be (UnOp("-", Number(6.78)))
  }

  test ("should parse an alpha unary operator") {
    ExprParser("sin xyzzy") should be (UnOp("sin", Var("xyzzy")))
    ExprParser("sin (xyzzy)") should be (UnOp("sin", Var("xyzzy")))
    ExprParser("sin(xyzzy)") should be (UnOp("sin", Var("xyzzy")))
  }

  test ("should parse unary operators correctly") {
    for (unop <- Seq("-", "sin", "abs", "cos")) {
      ExprParser(unop + "6.78") should be(UnOp(unop, Number(6.78)))
      ExprParser(unop + " 6.78") should be(UnOp(unop, Number(6.78)))
      ExprParser(unop + "(6.78)") should be(UnOp(unop, Number(6.78)))
      ExprParser(unop + "( 6.78 )") should be(UnOp(unop, Number(6.78)))
      ExprParser(unop + "(x)") should be(UnOp(unop, Var("x")))
      ExprParser(unop + "( x )") should be(UnOp(unop, Var("x")))
      ExprParser(unop + " xyzzy") should be(UnOp(unop, Var("xyzzy")))
      ExprParser(unop + "(xyzzy)") should be(UnOp(unop, Var("xyzzy")))
      ExprParser(unop + "( xyzzy )") should be(UnOp(unop, Var("xyzzy")))
    }
  }

  test ("should parse a simple binary operation") {
    ExprParser("(5+x)") should be (BinOp("+", Number(5.0), Var("x")))
  }

  test ("should parse various binary op with spaces in various positions") {
    for {
      varname <- Seq("x", "xyzzy")
      number <- Seq("5", "5.67")
    } {
      val compareVal = number.toDouble
      for (binop <- Seq("+", "-", "*", "/")) {
        println(s"(${varname} ${binop} ${number})")
        ExprParser(s"(${varname}${binop}${number})") should be(BinOp(binop, Var(varname), Number(compareVal)))
        ExprParser(s"(${varname} ${binop}${number})") should be(BinOp(binop, Var(varname), Number(compareVal)))
        ExprParser(s"(${varname} ${binop} ${number})") should be(BinOp(binop, Var(varname), Number(compareVal)))
        ExprParser(s"(${varname}${binop} ${number})") should be(BinOp(binop, Var(varname), Number(compareVal)))
        ExprParser(s"(${varname} ${binop} ${number} )") should be(BinOp(binop, Var(varname), Number(compareVal)))
        ExprParser(s"( ${varname} ${binop} ${number})") should be(BinOp(binop, Var(varname), Number(compareVal)))
        ExprParser(s"( ${varname} ${binop} ${number} )") should be(BinOp(binop, Var(varname), Number(compareVal)))
        ExprParser(s" ( ${varname} ${binop} ${number} ) ") should be(BinOp(binop, Var(varname), Number(compareVal)))
      }

      // for min and max - being alpha, you need the space after the var
      for (binop <- Seq("min", "max")) {
        println(s"(${varname} ${binop} ${number})")
        ExprParser(s"(${varname} ${binop} ${number})") should be(BinOp(binop, Var(varname), Number(compareVal)))
        ExprParser(s"(${varname} ${binop} ${number} )") should be(BinOp(binop, Var(varname), Number(compareVal)))
        ExprParser(s"( ${varname} ${binop} ${number})") should be(BinOp(binop, Var(varname), Number(compareVal)))
        ExprParser(s"( ${varname} ${binop} ${number} )") should be(BinOp(binop, Var(varname), Number(compareVal)))
        ExprParser(s" ( ${varname} ${binop} ${number} ) ") should be(BinOp(binop, Var(varname), Number(compareVal)))
      }
    }
  }

  test ("should handle some really deep expressions") {
    ExprParser("- (10.2 * (5 + xy))") should be
      (UnOp("-",BinOp("*",Number(10.2),BinOp("+",Number(5.0),Var("xy")))))

    ExprParser("-(10.2 * sin( 6+yz))") should be
      (UnOp("-", BinOp("*", Number(10.2), UnOp("sin", BinOp("+", Number(6.0), Var("yz"))))))
  }

}
