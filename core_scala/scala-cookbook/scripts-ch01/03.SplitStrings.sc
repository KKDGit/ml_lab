//1.3. Splitting Strings
println("----1.3. Splitting Strings-----")
val rs1 = "hello world".split(" ")

val rs2 = "hello world".split(' ')

rs1.getClass.getCanonicalName

rs2.getClass.getCanonicalName

val s = "eggs, milk, butter, Coco Puffs"

s.split(",")

s.split(",").map(_.trim)

val sp = "hello world, this is Al"

val r1 = sp.split(" ").map(_.trim)

val r2 = sp.split("\\s+")

println("--------Array Equallity -----------")
r1 == r2

r1.deep == r2.deep

r1.sameElements(r2)

r1.corresponds(r2) {_ == _}

// split with a String argument
"hello world".split(" ")

// split with a Char argument
"hello world".split(' ')
