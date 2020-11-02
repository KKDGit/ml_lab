package com.escalatesoft.demo

import scala.util.Random

class CalcPi {
  val random = new Random

  def draw(iterations: Int): Double = {
    val ct = (1 to iterations).count { _ =>
      val x = random.nextDouble()
      val y = random.nextDouble()
      math.sqrt(x * x + y * y) < 1.0
    }
    ct.toDouble / iterations
  }

  def draws(n: Int, iterations: Int): List[Double] = {
    var xs = List.empty[Double]
    for (i <- 1 to n) {
      xs = xs :+ draw(iterations)
    }
    xs
  }

  def calc(n: Int, iterations: Int): Double = {
    val xs = draws(n, iterations)
    xs.sum / xs.length
  }
}


object CalcPi extends App {
  val start = System.currentTimeMillis()
  val calcPi = new CalcPi
  println(calcPi.calc(20000, 50000) * 4)
  println(s"Took: ${System.currentTimeMillis() - start} ms")
}

