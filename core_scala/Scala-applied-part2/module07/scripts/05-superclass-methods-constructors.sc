abstract class Vehicle(val name: String, val age: Int) {
  override def toString: String =
    s"$name, $age years old"
}

class Car(
  override val name: String,
  val make: String,
  val model: String,
  override val age: Int
) extends Vehicle(name, age) {

  override def toString: String =
    s"a $make $model, named ${super.toString}"
}

val mustang = new Car("Sally", "Ford", "Mustang", 50)
