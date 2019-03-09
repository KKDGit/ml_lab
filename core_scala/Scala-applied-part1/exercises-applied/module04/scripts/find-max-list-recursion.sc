import scala.annotation.tailrec

@tailrec
    def max1(numbers : List[Int], maxSoFar:Int = Int.MinValue):Int = {
      if (numbers != Nil) {
        println(s"${numbers.head}, ${numbers.tail}, $maxSoFar")
        max1(numbers.tail, if(numbers.head > maxSoFar) numbers.head else maxSoFar)
      } else maxSoFar
    }

max1(List(1, 2, 3, 4, 5))
max1(List(5, 4, 3, 2, 1))
max1(List(-5, -1, -3))
max1(List(-1,-5, -1, -3,10))
max1(Nil)