trait Car1 {
  def color: String = s"red"
  def describe: String = s"$color car"
}

// val mustang1 = new Car1()  Compile Error
//Car1 is abstract ..can not be instantiated



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

class ActualCar1(val color: String, val name: String) extends AnyRef with Car
val modelT1 = new ActualCar1("black", "Model T")
modelT1.describe

val car: Car = modelT

car.describe


// we get all the functionality of Function1 by using it as trait
class Demo extends Car with Function1[String, String] {
  override def color = "red"
  override def apply(v1: String): String = s"$v1 $color"
}

val demo = new Demo

demo("cherry")

val descriptionLength = demo.andThen(_.length)

descriptionLength("cherry")