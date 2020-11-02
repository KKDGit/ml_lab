
/* Copyright (C) 2010-2019 Escalate Software, LLC. All rights reserved. */

package koans

import koans.support.KoanSuite
import org.scalatest.{SeveredStackTraces, Matchers,FunSuite}
import scala.util.Try

import scala.util.parsing.combinator.JavaTokenParsers

class Module15Solution extends FunSuite with Matchers with SeveredStackTraces {
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
    def minus: Parser[String] = "-"
    def abs: Parser[String] = "abs"
    def sin: Parser[String] = "sin"
    def cos: Parser[String] = "cos"

    // 1b - now make a validunop parser that will take any of the unops defined above
    def validunop: Parser[String] = minus|abs|sin|cos

    // 2a - next add the string literal tokens for our supported binops: "+", "*", "/", "min", "max"
    // note, you can use the same "-" definition from above in binops too, so no need to add
    // that again
    def plus: Parser[String] = "+"
    def times: Parser[String] = "*"
    def div: Parser[String] = "/"
    def min: Parser[String] = "min"
    def max: Parser[String] = "max"

    // 2b - and add the validbinop parser that takes any of the binops defined above (and also "-")
    def validbinop: Parser[String] = minus|plus|times|div|min|max

    // 3 -add a Parser[Number] for any floating point number and convert it to the Number case class
    def number: Parser[Number] = floatingPointNumber ^^ { n => Number(n.trim.toDouble) }

    // 4 - and a Parser[Var] for any valid ident and convert it to the Var case class
    def varnm: Parser[Var] = ident ^^ { v => Var(v.trim) }

    // the rest will have to be done in order, refining expr (at the bottom) as you go because
    // they forward reference expr, and it is currently not usefully defined.

    // 5 - define a Parser[UnOp] that combines validunop and an expr, and returns a new UnOp case class
    def unop: Parser[UnOp] = validunop ~ expr ^^ {
      case operator ~ expression => UnOp(operator, expression)
    }

    // I'll give you these ones, since they are pretty easy
    def openParen: Parser[String] = "("
    def closeParen: Parser[String] = ")"

    // 6 - now use the above definitions (and expr which will be filled in below) to
    // create a Parser[BinOp] that starts with an opening paren, then expr, then
    // valid bin op, then another expr and finally a closing paren. For simplicity
    // we are going to assume that binops are always enclosed in parens, but if you
    // want to go further, try making the parens optional some time (finish the
    // rest of the exercises first though)
    def binop: Parser[BinOp] = openParen ~> expr ~ validbinop ~ expr <~ closeParen ^^ {
      case ex1 ~ op ~ ex2 => BinOp(op, ex1, ex2)
    }

    // 7 - Make a Parser[Expr]s that can handle naked expressions (any
    // unop, number, binop or var)
    def nakedexpr: Parser[Expr] = unop|number|binop|varnm


    // 8 - Now make another Parser[Expr] that can handle any expression (as
    // defined above) that has a set of parens around it.
    def parenexpr: Parser[Expr] = openParen ~> nakedexpr <~ closeParen


    // 9 - finally make a Parser[Expr] that can take either the naked expr
    // (no parens) or the paren enclosed expression
    // Note that you will probably need to keep refining expr as you run your
    // tests because other parsers reference it, but in the end it should
    // be defined only in terms of naked expressions and paren expressions.
    def expr: Parser[Expr] = nakedexpr|parenexpr

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
