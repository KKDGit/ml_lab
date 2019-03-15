import scala.collection.mutable
import scala.collection.immutable

def popImmutableQueue(q: immutable.Queue[Int]): (Int, immutable.Queue[Int]) = {
  q.dequeue
}

def popMutableQueue(q: mutable.Queue[Int]): Int = {
  q.dequeue()
}


val immQ = immutable.Queue(1,2,3,4)
val mQ = mutable.Queue(1,2,3,4)

popImmutableQueue(immQ)
popMutableQueue(mQ)

immQ
mQ
