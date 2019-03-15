/* Copyright (C) 2010-2018 Escalate Software, LLC. All rights reserved. */
package lab.awesome.laser


class Gun(power:Int) {
  class Beam(power:Int) {
    def lumens: Int = power * 10
  }
  def shoot(): Beam = new Beam(power)
}



