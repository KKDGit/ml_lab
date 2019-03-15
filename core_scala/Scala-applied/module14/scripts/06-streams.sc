val numsFromOne = Stream.from(1)
val zipped = numsFromOne.zip(Stream.from(2))

val get = zipped.take(10).toList

