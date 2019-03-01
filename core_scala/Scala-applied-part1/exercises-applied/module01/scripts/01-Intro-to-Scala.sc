1 + 2

val x = 1 + 2

x * 2

//x = x * 2   // will not compile, try it

var y = 2

y = y * 2

// y = "four"  // also will not compile - types must be the same

def add(x: Int, y: Int) = x + y

add(2, 3)

def addAgain(x: Int, y: Int): Int = {
  x + y
}

addAgain(2, 3)

var a = 10
var b = 12

if (a > b) {
  println(a)
}
else {
  println(b)
}

val m = if (a > b) a else b

def maxSquaredDoubled(a: Int, b: Int): Int =
  if (a > b) {
    val squared = a * a
    squared * 2
  }
  else {
    val squared = b * b
    squared * 2
  }

maxSquaredDoubled(5, 3)
maxSquaredDoubled(3, 5)
a = 15
b = 15

val divided = try {
  a / (b - 12)
}
catch {
  case ae: ArithmeticException => 0
}
finally {
  println("this is final block of divided fun")
  100
}

a = 100
b = 12
val divided_1 = try {
  a / (b - 12)
}
catch {
  case ae: ArithmeticException => throw new
      RuntimeException("can't divide by Zero")
}
finally {
  println("this is final block of divided_1 fun")
  100
}
