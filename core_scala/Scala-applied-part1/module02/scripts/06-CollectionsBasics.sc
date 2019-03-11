val array1: Array[Int] = Array(1,2,3)
val list1: List[String] = List("scooby", "dooby", "doo")

val array2 = Array(1,2,3)
val list2 = List("scooby", "dooby", "doo")
val list3 = List(1, 2, 3, 4)

def squareRootsOf(xs: List[Int]): List[Double] = {
  for (x <- xs) yield math.sqrt(x)
}
squareRootsOf(List(1,2,3,4,5,6))

def lengthOfList(xs: List[Any]) = xs.size

println(s"lengthOfList(list3) = ${lengthOfList(list3)}")
println(s"lengthOfList(list2) = ${lengthOfList(list2)}")

// type parameters are not optional, this will not compile:
// def badSquareRootsOf(xs: List): List = {
//   for (x <- xs) yield math.sqrt(x)
// }

// List initializers

val lista = List(1,2,3)

val listb = 4 :: 5 :: 6 :: Nil

val listc = lista ::: listb

// common beginner mistake:

val listd = lista :: listb

val v = Vector(1,2,3,4)

def squareRootOfAll(xs: Seq[Int]): Seq[Double] =
  xs.map(x => math.sqrt(x))

squareRootOfAll(v)
squareRootOfAll(listc)
squareRootOfAll(array2)


val set1 = Set(1,2,3,1,2,4,5)

// squareRootOfAll(set1) // does not compile
new Array[Int](3)
new Array[String](3)

var varArray1 = Array(4,5,6)