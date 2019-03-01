def opposite(s: String): String = s match {
  case "hot"         => "cold"
  case "full"        => "empty"
  case "cool"        => "square"
  case "happy"       => "sad"
  case anythingElse  => s"not $anythingElse"
}

opposite("cool")
opposite("happy")
opposite("sane")


def opposite2(s: String): String = s match {
  case "hot"         => "cold"
  case "full"        => "empty"
  case "cool"        => "square"
  case "happy"       => "sad"
  case inWord @ ("sane" | "edible" | "secure") =>
    s"in$inWord"
  case anythingElse => s"not $anythingElse"
}

opposite2("happy")
opposite2("sane")
opposite2("edible")
opposite("fish")


// case matters for variable vs constant

val MaxLimit = 10  // constants start with upper case
val minLimit = 1

def isALimit(x: Int) = x match {
  case MaxLimit => true
  case `minLimit` => true
  case _ => false
}

isALimit(10)
isALimit(3)
