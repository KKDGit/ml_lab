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
val minLimit = 3
val MY_HUNDRED = 100

def isALimit(x: Int) = x match {
  case MaxLimit => 10
  case `minLimit` => 3
  case MY_HUNDRED => 100
  case _ => -1
}

isALimit(10)
isALimit(3)
isALimit(100)
isALimit(1)


val MaxLimit1 = 10  // constants start with upper case
val minLimit1 = 3

def isALimit1(x: Int) = x match {
  case MaxLimit1 => 10
  case minLimit1 => 3
  case MY_HUNDRED => 100
  case _ => -1
}

isALimit1(10)
isALimit1(1)
isALimit1(100)
isALimit1(1)

