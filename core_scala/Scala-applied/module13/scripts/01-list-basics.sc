val oneTwo = List(1,2)
val threeFour = 3 :: 4 :: Nil
val oneTwoThreeFour = oneTwo ::: threeFour


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


List.fill(10)(0)
List.tabulate(10)(x => x * x)
List.range(0, 10)
