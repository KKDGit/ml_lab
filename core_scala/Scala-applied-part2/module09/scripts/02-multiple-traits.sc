abstract class Car {
  def color: String
  def describe: String = s"$color"
  override def toString = s"$describe car"
}

trait Classic extends Car {
  def vintage: Int
  override def describe: String =
    s"vintage $vintage ${super.describe}"
}

trait Convertible extends Car {
  def poweredTop: Boolean
  override def describe: String = {
    val top = if (poweredTop)
      "powered convertible" else "convertible"
    s"$top ${super.describe}"
  }
}

class ClassicConvertible(
  val color: String, val vintage: Int, val poweredTop: Boolean
) extends Car with Classic with Convertible

val mustang = new ClassicConvertible("red", 1965, false)

