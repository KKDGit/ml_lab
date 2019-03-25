val words = List("one","four", "for", "char", "word")

words.map(_.reverse)

words.reverse.map(_.reverse)

val wordList = words.map { word => word.toList }
wordList.flatten

words.flatMap { word => word.toList }

words foreach println

words.filter(_.contains("a"))
words.filter(_.contains("f"))

words.find(_.contains("a"))
words.find(_.contains("z"))
//it gives option

words.indexWhere(_.contains("a"))
words.indexWhere(_.contains("z"))
words.indexWhere(_.contains("r"))
words.lastIndexWhere(_.contains("r"))

words.filterNot(_.contains("a"))
words.partition(_.contains("o"))
//partition will take the elements from beginning until end
// using predicate

words.span(_.contains("o"))
//span will take the elements from beginning until it
//predicate becomes false for the first time

words.takeWhile(_.contains("f"))
words.dropWhile(_.contains("f"))

val data = List(6, 1, 2, 3, 4, 4, 5, 5, 7)
val res = data.span(_ < 5)
val part = data.partition(_ < 5)

val res1 = data.span(_ > 5)
val part1 = data.partition(_ > 5)