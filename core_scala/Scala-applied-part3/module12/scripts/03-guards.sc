// The correct way to use guards
def describeNumber(x: Int): String = x match {
  case 0                      => "zero"
  case n if n > 0 && n < 100  => "smallish positive"
  case n if n > 0             => "large positive"
  case n if n < 0 && n > -100 => "smallish negative"
  case _                      => "large negative"
}

describeNumber(-99)
describeNumber(99)
describeNumber(0)
describeNumber(101)
describeNumber(-101)

// This is the wrong way to do it!
def badDescribeNumber(x: Int) = x match {
  case 0 =>                        "zero"
  case n => if (n > 0 && n < 100)  "smallish positive"
  case n => if (n > 0)             "large positive"
  case n => if (n < 0 && n > -100) "smallish negative"
  case _ =>                        "large negative"
}

badDescribeNumber(-99)
badDescribeNumber(99)
badDescribeNumber(0)
badDescribeNumber(101)
badDescribeNumber(-101)
