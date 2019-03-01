// like Java switch

def matchIt(x: Any): Unit = x match {
  case 10        => println("The number 10")
  case true      => println("This is the truth")
  case 2.0       => println("Double precision 2.0")
  case "hello"   => println("Well, hi there")
  case ()        => println("Unit")
  case _         => println("It's something else")
}

matchIt(10)
matchIt(2.0)
matchIt("hello")
matchIt(())
matchIt(3)


// but also an expression

def pair(s: String): String = s match {
  case "fish"     => "chips"
  case "bacon"    => "eggs"
  case "tea"      => "scones"
  case "horse"    => "carraige"
}

pair("fish")
pair("tea")

pair("universe")
