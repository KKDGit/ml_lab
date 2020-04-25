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

//pair("universe")

def matchVal(x: AnyVal): Unit = x match {
  case 10        => println("The number 10")
  case true      => println("This is the truth")
  case 2.0       => println("Double precision 2.0")
  //case "hello"   => println("Well, hi there")
  //String is not AnyVal(Primitive Data Type)
  case ()        => println("Unit")
  case _         => println("It's something else")
}

matchVal(10)
matchVal(2.0)
//matchVal("hello")
matchVal(())
matchVal(3)


def matchRef(x: AnyRef): Unit = x match {
//  case 10        => println("The number 10")
//  case true      => println("This is the truth")
//  case 2.0       => println("Double precision 2.0")
  // 10, true, 2.0 are not AnyRef
  case "hello"   => println("Well, hi there")
 // case ()        => println("Unit")
  case _         => println("It's something else")
}

//matchRef(10)
//matchRef(2.0)
matchRef("hello")
matchRef(List.empty)
//matchRef(())
//matchRef(3)