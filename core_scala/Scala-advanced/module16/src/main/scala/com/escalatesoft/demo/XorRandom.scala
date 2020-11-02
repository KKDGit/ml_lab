package com.escalatesoft.demo

import scala.util.Random

class XorRandom(seed: Long) extends java.util.Random {
  private[this] var _seed = seed

  // not thread safe - need a separate instance for each thread!
  override protected def next(nbits: Int): Int = {
    var x = this._seed
    x ^= (x << 21)
    x ^= (x >>> 35)
    x ^= (x << 4)
    this._seed = x
    x &= ((1L << nbits) - 1)
    x.toInt
  }
}

object XorRandom {
  def random(seed: Long = Random.nextLong()): Random = {
    new Random(new XorRandom(seed))
  }
}