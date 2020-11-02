def timeIt[A](name: String)(fn: => A): A = {
  val startTime = System.currentTimeMillis()
  val result = fn
  val elapsed = System.currentTimeMillis() - startTime
  println(s"$name took $elapsed ms")
  result
}

def fibs1(limit: Int): List[Long] = {
  (0 to limit).foldLeft(List.empty[Long]) { (acc, i) =>
    if (i < 2) acc :+ i.toLong
    else acc :+ acc.takeRight(2).sum
  }
}

timeIt("list append") {
  fibs1(20000)
}

def fibs2(limit: Int): Vector[Long] = {
  (0 to limit).foldLeft(Vector.empty[Long]) { (acc, i) =>
    if (i < 2) acc :+ i.toLong
    else acc :+ acc.takeRight(2).sum
  }
}

timeIt("vector append") {
  fibs2(20000)
}

def fibs3(limit: Int): List[Long] = {
  (0 to limit).foldLeft(List.empty[Long]) { (acc, i) =>
    if (i < 2) i :: acc else acc.take(2).sum :: acc
  }
}

timeIt("list prepend") {
  fibs3(20000)
}

def fibs4(limit: Int): Array[Long] = {
  val arr = new Array[Long](limit + 1)
  arr(0) = 0
  var x = 0
  var a = 0
  var b = 1
  while (x < limit) {
    x += 1
    val temp = a + b
    b = a
    a = temp
    arr(x) = a
  }
  arr
}

timeIt("array") {
  fibs4(100000)
}