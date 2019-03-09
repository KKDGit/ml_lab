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

mustang.name

class Car1(
name: String,
val make: String,
val model: String,
age: Int
) extends Vehicle(name="SuperSally", age) {

  override def toString: String =
  s"a $make $model, named ${super.toString}"

  def printAll: String =
    s"name $name, age $age, super ${super.toString}"
}

val mustang1 = new Car1("SubSally", "Ford", "Mustang", 50)

mustang1.name
mustang1.age
mustang1.printAll