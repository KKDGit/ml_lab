val words = List("four", "four", "char", "word")
val nums = List(2,3,5,8,13,21)

val sumNums = nums.foldLeft(0)((a, b) => a + b)
val prodNums = nums.foldLeft(1)(_ * _)

val asString = words.foldLeft("")(_ + ", " + _)

val sum2 = nums.reduceLeft(_ + _)

List.empty[Int].foldLeft(0)(_ + _)

// can also use

nums.sum
nums.product

words.toString
words.mkString
words.mkString(",")
words.mkString("[", ",", "]")


// beware reduce on empty Lists
List.empty[Int].reduceLeft(_ + _)



