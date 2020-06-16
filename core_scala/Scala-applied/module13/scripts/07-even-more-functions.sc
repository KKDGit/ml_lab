val matrix = List(List(1,2,3), List(4,5,6), List(7,8,9))
val transpose = matrix.transpose
matrix.flatten.sum


val words = List("for", "four", "char", "word")
val grpd = words.groupBy(_.head)
grpd.mapValues(_.size)

trait Fruit extends Product with Serializable
case class Apple(name: String) extends Fruit
case class Orange(name: String) extends Fruit
val fruits = List(
  Apple("Fiji"),
  Orange("Jaffa"),
  Apple("Cox's"))

fruits.collect {
  case a: Apple => a
}
fruits.filter {
  case _: Apple => true
  case _: Orange => false
}

val nums = List.range(0, 10)
nums.lift(util.Random.nextInt(nums.length))
nums.lift(3)
List.empty.lift(4)
nums.grouped(3).take(5).toList
nums.sliding(4).take(5).toList
nums.sliding(4,2).take(5).toList
nums.combinations(4).take(5).toList

scala.util.Random.shuffle(nums).
  take(3).permutations.toList
val nums1 = (List.fill(5)(0) :::
  List.fill(5)(1) :::
  List.fill(5)(2) :::
  List.fill(5)(3))

scala.util.Random.shuffle(nums1).
  take(4).permutations.take(3).toList

scala.util.Random.shuffle(nums1.toSet).toList.
  take(4).permutations.take(3).toList

val numsPlusOne = nums.map(_ + 1)
nums.corresponds(numsPlusOne)(
  (a, b) => a + 1 == b)

val chars = List.range('a', 'h')
val idx = chars.indices
chars.zip(idx)
val zipped = chars.zipWithIndex
zipped.unzip