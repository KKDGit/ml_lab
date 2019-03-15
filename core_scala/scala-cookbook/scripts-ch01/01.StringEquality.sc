"Hell0, World".getClass.getName
val p1 = "hello" + " world"

val s = "Hello, World"
s.length


"hello".foreach(println)
for (c <- s) println(c)

s.getBytes
s.filter(_ != 'l')
s.filter(_ != "l")

'l'.getClass.getName
"l".getClass.getName

"scala".drop(2).take(2).capitalize
"scala".slice(2,4).capitalize
//1.1. Testing String Equality

println("--------1.1. Testing String Equality----------")
val s1 = "Hello"
val s2 = "Hello"
val s3 = "H" + "ello"

s1 == s2

s1 == s3

//A pleasant benefit of the == method is that it doesn’t throw a NullPointerException
//on a basic test if a String is null
val s4: String = null
s3 == s4

s4 == s3

//you want to compare two strings in a case strings to uppercase or lowercase and compare
val sa1 = "Hello"
val sa2 = "hello"
sa1.toUpperCase == sa2.toUpperCase

//However, be aware that calling a method
val sb1: String = null
val sb2: String = null

// sb1.toUpperCase == sb2.toUpperCase //Runtime Error

val a = "Marisa"
val b = "marisa"
a.equalsIgnoreCase(b)

