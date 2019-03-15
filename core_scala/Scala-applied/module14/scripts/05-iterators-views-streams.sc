val nums = List.range(1, 21)

val numsIter = nums.iterator

numsIter.hasNext

// An example of what not to do!
// if (numsIter.length > 0) numsIter.next()


val vec = Vector.range(0, 20)

val vecView = vec.view

def calcSquare(x: Int): Int = {
  println(s"Calculating for $x")
  x * x
}

val squaresView = vecView.map(calcSquare)

squaresView(2)
squaresView(4)
squaresView(2)

val squares = squaresView.force

squares(2)

squares


val numsFromOne = Stream.from(1)

val firstTenNums = numsFromOne.take(10)
firstTenNums.toList

val powersOfTwo: Stream[Int] = 1 #:: powersOfTwo.map(_ * 2)
powersOfTwo.take(5).toList

val factorial: Stream[BigInt] = BigInt(1) #:: factorial.zip(Stream.from(2)).
  map { case(a, b) => a * b }

val firstTenFacs = factorial.take(10)

firstTenFacs.toList


val fibs: Stream[BigInt] = BigInt(0) #:: BigInt(1) #:: fibs.zip(fibs.tail).
  map { case(x, y) => x + y }

fibs.take(20).toList
