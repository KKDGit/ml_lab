import java.time.LocalDate

class Person(name: String, age: Int) {
  def isAdult: Boolean = age >= 21
}

val p1 = new Person("Dave", 18)
val p2 = new Person("Jill", 25)

p1.isAdult
p2.isAdult

// always get a new instance, vs string interning

"hello".eq("hello")

new String("hello").eq(new String("hello"))

abstract class Car(make: String, model: String, year: Int) {
  def isVintage: Boolean = LocalDate.now.getYear - year > 20
}

// will not compile
// val mustang = new Car("Ford", "Mustang", 1965)

// but this will
val mustang = new Car("Ford", "Mustang", 1965) {}