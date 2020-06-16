val words = List("four", "four", "char", "word")
val words1 = List("", "four", "r", "wo")
val words2 = List("kkkkd", "lllkkk", "r", "wo")
val nums = List(2,3,5,8,13,21)

val sumNums = nums.foldLeft(0)((a, b) => 1 + a + b)
val prodNums = nums.foldLeft(1)(_ * 1 *_)

val asString = words.foldLeft("")(_ + ", " + _)



def wordsFoldLeft(words5: List[String]):
(Boolean, Boolean, Boolean) = {
  val k1 = words5.foldLeft(true) { (isFour, ele) =>
    isFour && ele.length == 4
  }
  val k3 = words5.forall(ele => ele.length == 4)

  val k2 = words5.foldLeft(false) { (isFour, ele) =>
    isFour || ele.length == 4
  }
  (k1, k3, k2)
}
words
wordsFoldLeft(words)
words1
wordsFoldLeft(words1)
words2
wordsFoldLeft(words2)

val sum2 = nums.reduceLeft(_ + 1 + _)

//List.empty[Int].reduceLeft(_ + 1 + _)
//java.lang.UnsupportedOperationException: empty.reduceLeft
List.empty[Int].foldLeft(0)(_ + 1 + _)

// can also use
nums.foldLeft(0)(_ + _)
nums.foldLeft(1)(_ * _)
nums.sum
nums.product

words.toString
words.mkString
words.mkString(",")
words.mkString("[", ",", "]")


// beware reduce on empty Lists
List.empty[Int].reduceLeft(_ + _)



