val words = List("one","four", "for", "char", "word")

words.map(_.reverse)

words.reverse.map(_.reverse)

val wordList = words.map { word => word.toList }
wordList.flatten

words.map { word => word.toList }.flatten
words.flatMap(_.toList)
words.flatMap { word => word.toList }

words foreach println

//predicate based functions
//this includes for the elements
// where predicate is true
words.filter(_.contains("a"))
words.filter(_.contains("f"))

words.find(_.contains("a"))
words.find(_.contains("z"))
//it gives option

words.indexWhere(_.contains("a"))
words.indexWhere(_.contains("z"))
//if predicate is not true it returns -1
words.indexWhere(_.contains("r"))
words.lastIndexWhere(_.contains("r"))

words.filterNot(_.contains("a"))
val pat1 = words.partition(_.contains("o"))
val pat2 = (words.filter(_.contains("a")),
  words.filterNot(_.contains("a")))

//partition will take the elements from
//beginning until end using predicate
words
words.span(_.contains("o"))
words.partition(_.contains("o"))
// span will take the elements from beginning
// until it predicate becomes false
// for the first time
words
words.takeWhile(_.contains("o"))
words.dropWhile(_.contains("o"))

val data = List(6, 1, 2, 3, 4, 4, 5, 5, 7)
val res = data.span(_ < 5)
val part = data.partition(_ < 5)

val res1 = data.span(_ > 5)
val part1 = data.partition(_ > 5)