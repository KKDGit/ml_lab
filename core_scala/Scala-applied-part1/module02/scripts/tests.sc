val greetStrings = new Array[String](3)
greetStrings(0) = "Hello"
greetStrings(1) = ", "
greetStrings(2) = "World"

println(s"greetStrings == ${greetStrings.mkString(" ")}")
// what happens if you replace the above line with:
//greetStrings(3) = 2 //will not compile