import java.time.LocalDate

abstract class Car(
  val make: String,
  val model: String,
  val year: Int
) {
  def isVintage: Boolean
}

val mustang = new Car("Ford", "Mustang", 1965) {
  def isVintage = LocalDate.now.getYear - year > 20
}