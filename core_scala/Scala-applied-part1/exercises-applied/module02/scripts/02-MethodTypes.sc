def max(x: Int, y: Int): Int =
  if (x > y) x else y

def min(x: Int, y: Int) =
  if (x < y) x else y

def sayHi(name: String): Unit =
  println(s"hello $name")

sayHi("Scala class")


//NEVER use Procedural Syntax
def procedureSyntax(name: String) {
  println(s"hello $name")
}

procedureSyntax("Hi Procedural")

def add(a: Int, b:Int) {
 a+b
}
add(2,3)

def add1(a: Int, b:Int){
  a+b
}

add1(3,6)