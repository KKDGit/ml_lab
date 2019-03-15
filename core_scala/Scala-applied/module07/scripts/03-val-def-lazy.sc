class Demo {
  val a: Int = {
    println("evaluating a")
    10
  }
  def b: Int = {
    println("evaluating b")
    20
  }
  lazy val c: Int = {
    println("evaluating c")
    30
  }
}

val demo = new Demo

val demo1 = new Demo //all vals are evaluated but not defs and lazy vals

demo.a //

demo.b //always evaluated since it is def

demo.b

demo.c //first time call lazy val will be evaluated

demo.c
