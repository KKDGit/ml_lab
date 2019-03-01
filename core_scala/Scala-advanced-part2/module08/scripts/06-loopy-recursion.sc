import scala.collection.mutable

def factSeq(n: Int): List[Long] = {
  def fact(v: Int): Long = {
    var prod = 1L
    for (i <- 1 to v) prod *= i
    prod
  }
  val buf = mutable.ArrayBuffer.empty[Long]
  for (i <- 1 to n) buf.append(fact(i))
  buf.toList
}

factSeq(8)

@scala.annotation.tailrec
def factSeqFun(lim: Int, cur: Int = 2,
  xs: List[Long] = List(1L)): List[Long] =
  if (cur > lim) xs.reverse else
    factSeqFun(lim, cur + 1, cur * xs.head :: xs)

factSeqFun(8)

