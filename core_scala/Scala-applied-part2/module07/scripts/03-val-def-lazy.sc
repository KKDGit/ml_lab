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
demo.a
demo.b
demo.b
demo.c
demo.c
