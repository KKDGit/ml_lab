val x = 1 + 2

val y = 1.+(2)

val s = "hello"

s.charAt(1)
s charAt 1

// println "hello" // will not compile

System.out println "hello"

// --- apply and update

val arr = Array("scooby", "dooby", "doo")

arr.apply(1)

arr(0)

arr.update(0, "scrappy")

arr(1) = "dappy"

println(s"array.deep = ${arr.deep}")

println(s"arr = $arr")

println(s"arr.toString = ${arr.toString}")

val arr2 = Array.apply(1,2,3)

val z = 10
// z(2) // does not compile

val xs = List(1,2,3)
xs(1)  // works
// xs(1) = 10 // does not compile

//println(s"list.deep = ${xs.deep}") //there is no deep here

println(s"list = $xs")

println(s"list.toString = ${xs.toString}")
