val empty = List.empty
val one = 1 :: empty
val trueOne = true :: 1 :: empty
val strTrueOne = "kkd" :: trueOne

def listSize(xs: List[Any]): Int = xs.size
listSize(empty)
listSize(one)
listSize(trueOne)
listSize(strTrueOne)
empty eq Nil
empty eq List.empty
empty eq Seq.empty
empty eq Vector.empty
Seq.empty eq Set.empty

val oneTwo = List(1,2)
val threeFour = 3 :: 4 :: Nil
val oneTwoThreeFour = oneTwo ::: threeFour
val otherThreeFour = 3 :: 4 :: Nil

oneTwoThreeFour.tail.tail.equals(threeFour)
oneTwoThreeFour.tail.tail.eq(threeFour)
oneTwoThreeFour.tail.tail == threeFour

oneTwoThreeFour.tail.tail.equals(otherThreeFour)
oneTwoThreeFour.tail.tail.eq(otherThreeFour)
oneTwoThreeFour.tail.tail == otherThreeFour

List.fill(10)(0)
List.tabulate(10)(x => x * x)
List.range(0, 10)
List.range(0, 20, 2)

Vector('a', 'b', 'c').toList
Set(1.0, 2.0, 3.0).toList
Map(1 -> "one", 2 -> "two").toList
"hello world".toList

val xs1 = 1 :: Nil
val xs2 = true :: xs1
val xs3 = "hi" :: xs2

def sizeOfList(xs: List[Any]): Int = xs.size

sizeOfList(xs1)
sizeOfList(xs2)
sizeOfList(xs3)
