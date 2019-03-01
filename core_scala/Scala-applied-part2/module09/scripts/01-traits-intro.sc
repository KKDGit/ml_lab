trait Car {
  def color: String
  def describe: String = s"$color car"
}

val mustang = new Car {
  val color = "red"
}

mustang.describe

class ActualCar(val color: String, val name: String) extends Car

val modelT = new ActualCar("black", "Model T")

modelT.describe

val car: Car = modelT

car.describe

class Demo extends Car with Function1[String, String] {
  override def color = "red"
  override def apply(v1: String): String = s"$v1 $color"
}

val demo = new Demo

demo("cherry")

val descriptionLength = demo.andThen(_.length)

descriptionLength("cherry")