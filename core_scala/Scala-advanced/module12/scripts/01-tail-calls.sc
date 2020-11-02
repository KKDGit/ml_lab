import scala.annotation.tailrec

def fact(n: Int): Long = {
  var i = 1
  var acc = 1

  while (i < n) {
    acc += i * acc
    i += 1
  }

  acc
}

fact(8)

def fact2(n: Int): Long = {
  if (n < 2) n else n * fact2(n - 1)
}

fact2(8)
//fact2(9999999)

@tailrec
def fact3(n: Int, acc: Long = 1): Long = {
  if (n < 2) acc else fact3(n - 1, acc * n)
}

fact3(8)
fact3(9999999)

def fib(n: Int): Long =
  n match {
    case 0 => 0
    case 1 => 1
    case x => fib(x - 1) + fib(x - 2)
  }

fib(8)
//fib(9999999)