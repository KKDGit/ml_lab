def describeSignIf(n: Int): String =
  if (n == 0) "Zero" else
    if (n > 0) "Positive" else
      "Negative"


def describeSignMatch(n: Int): String = n match {
  case 0 => "Zero"
  case v if v > 0 => "Positive"
  case _ => "Negative"
}

case class LDResult(r2: Double, dPrime: Double, valid: Boolean, linked: Boolean)

def ldLinked(ldResult: LDResult): String = ldResult match {
  case LDResult(_, _, _, true) => "linked"
  case _ => "not linked"
}

ldLinked(LDResult(0.8, 0.8, true, true))
ldLinked(LDResult(0.8, 0.8, true, false))