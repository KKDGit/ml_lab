class Demo

class DemoWithFieldsAndMethods {
  val x = 10
  val y = x * 2

  def timesY(a: Int): Int = a * y
}

val demoWithFieldsAndMethods = new DemoWithFieldsAndMethods

demoWithFieldsAndMethods.x
demoWithFieldsAndMethods.timesY(4)

class DemoWithParams(name: String) {
  println(s"Constructing for $name")

  def sayHi(times: Int): Unit = {
    var time = 0

    while (time < times) {
      println(s"Hi, $name")
      time += 1
    }
  }
}

val demoWithParams = new DemoWithParams("Joey")

demoWithParams.sayHi(5)

// can't access the name from outside:
// demoWithParams.name

class DemoWithParams1(val name: String) {
  println(s"Constructing for $name")

  def sayHi(times: Int): Unit = {
    var time = 0

    while (time < times) {
      println(s"Hi, $name")
      time += 1
    }
  }
}
val demoWithParams1 = new DemoWithParams1("kk")
demoWithParams1.name
// can access the name from outside: it is parametric field