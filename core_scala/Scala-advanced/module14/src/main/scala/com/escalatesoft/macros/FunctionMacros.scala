package com.escalatesoft.macros

import scala.language.experimental.macros
import scala.reflect.macros.blackbox


class NamedFunction[-T, +R](val name: String, fn: T => R) extends (T => R) {
  def apply(x: T): R = fn(x)
  override def toString: String = name
}


object FunctionMacros {
  def describeFn[T, R](expr: T => R): NamedFunction[T, R] = macro namedImpl[T, R]

  def namedImpl[T, R](c: blackbox.Context)(expr: c.Expr[T => R]): c.Expr[NamedFunction[T, R]] = {
    import c.universe._
    val name = expr.tree match {
      case q"$fn" => fn.toString
    }
    if (name.length > 100) c.warning(c.enclosingPosition,
      s"Function definition is too long to be named, please move the function literal into a method and call that instead")

    c.Expr[NamedFunction[T, R]](q"""new NamedFunction($name, $expr)""")
  }
}