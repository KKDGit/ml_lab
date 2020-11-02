package com.escalatesoft.macros

import scala.language.experimental.macros
import scala.reflect.macros.blackbox

object SimpleMacro {
  def describeFn[T, R](expr: T => R): T => R = macro namedImpl[T, R]

  def namedImpl[T, R](c: blackbox.Context)
    (expr: c.Expr[T => R]): c.Expr[T => R] = expr
}