abstract class Vehicle {
  def describe: String  // abstract describe
  override def toString = s"$describe"
}

trait Classic extends Vehicle {
  def vintage: Int
  abstract override def describe: String =
    s"vintage $vintage ${super.describe}"
}

trait Convertible extends Vehicle {
  def poweredTop: Boolean
  abstract override def describe: String = {
    val top = if (poweredTop)
      "powered convertible" else "convertible"
    s"$top ${super.describe}"
  }
}

trait Car extends Vehicle {
  def color: String
  def describe: String = s"$color car"
}

class ClassicConvertible(
  val color: String, val vintage: Int, val poweredTop: Boolean
) extends Car with Classic with Convertible

val mustang = new ClassicConvertible("red", 1965, false)

