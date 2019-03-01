val x = 2

require(x > 0, "x must be positive")

import org.scalactic.Requirements._

// requireState(x < 0, "x must be negative")

def square(x: Int): Int = {
  x * x
} ensuring (_ >= 0)

square(10)

-1.0.abs ensuring (_ > 0.0)

