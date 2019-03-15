val matrix = List(List(1,2,3), List(4,5,6), List(7,8,9))

val transpose = matrix.transpose

matrix.flatten.sum




trait Fruit extends Product with Serializable
case class Apple(name: String) extends Fruit
case class Orange(name: String) extends Fruit

val fruits = List(Apple("Fiji"), Orange("Jaffa"), Apple("Cox's"))

fruits.collect {
  case a: Apple => a
}


val words = List("four", "four", "char", "word")

words.groupBy(_.head).mapValues(_.size)


val nums = List.range(0, 10)
nums.grouped(3).take(5).toList
nums.sliding(3,2).take(5).toList
nums.combinations(3).take(5).toList
nums.permutations.take(5).toList

val numsPlusOne = nums.map(_ + 1)

nums.corresponds(numsPlusOne)((a, b) => a + 1 == b)


val chars = List.range('a', 'h')

val idx = chars.indices

chars.zip(idx)

val zipped = chars.zipWithIndex

zipped.unzip
