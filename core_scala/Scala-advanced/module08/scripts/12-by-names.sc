import scala.annotation.tailrec

@tailrec
def fruitLoop(p: => Boolean)(fn: => Unit): Unit = {
  if (p) {
    fn
    fruitLoop(p)(fn)
  }
}

var x = 0
fruitLoop(x < 10) {
  println(x * x)
  x += 1
}

case class FruitLoopStep(p: Boolean, fn: Unit)

@tailrec
def fruitLoopInner(fl: FruitLoopStep): Unit = {
  if (fl.p) {
    fl.fn
    fruitLoopInner(fl)
  }
}

def fruit(p: => Boolean)(fn: => Unit) =
  fruitLoopInner(FruitLoopStep(p, fn))

var i = 0
// if we change the test to < 1 now?
fruit(i < 0) {
  println(i * i)
  i += 1
}
