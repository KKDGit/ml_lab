import java.time.LocalDate

case class Car(make: String, model: String, year: Int) {
  lazy val isVintage: Boolean =
    LocalDate.now.getYear - year > 20
}

val mustang = Car("Ford", "Mustang", 1965)

mustang.make
mustang.model
mustang.year
mustang.isVintage

mustang == Car("Ford", "Mustang", 1965)
mustang == Car("Ford", "Mustang", 1964)

class Car1(val make: String, val model: String, val year: Int) {
  lazy val isVintage: Boolean =
    LocalDate.now.getYear - year > 20
}

val mustang1 = new Car1("Ford", "Mustang", 1965)

mustang1.make
mustang1.model
mustang1.year
mustang1.isVintage

mustang1 == new Car1("Ford", "Mustang", 1965)
mustang1 == new Car1("Ford", "Mustang", 1964)
