
/* Copyright (C) 2010-2018 Escalate Software, LLC. All rights reserved. */

package koans.support
import org.scalatest.FunSuite

abstract class KoanSuite extends FunSuite with StopOnFirstFailure {
  def koan(name : String)(fun: => Unit) = test(name)(fun)
}

