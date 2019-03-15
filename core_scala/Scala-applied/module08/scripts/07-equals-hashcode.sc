class Person(val first: String, val last: String, val age: Int) {

  def canEqual(other: Any): Boolean = other.isInstanceOf[Person]

  override def equals(other: Any): Boolean = other match {
    case that: Person =>
      (that canEqual this) &&
        first == that.first &&
        last == that.last &&
        age == that.age
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(first, last, age)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}

val p1 = new Person("Wavey", "Davey", 25)
val p2 = new Person("Wavey", "Davey", 25)

p1 == p2
p1.##
p2.##

class Fruit(val name: String) {

  def canEqual(other: Any): Boolean = other.isInstanceOf[Fruit]

  override def equals(other: Any): Boolean = other match {
    case that: Fruit =>
      (that canEqual this) &&
        name == that.name
    case _ => false
  }

  override def hashCode(): Int = name.hashCode
}

class Apple(val brand: String, val color: String) extends Fruit("apple") {

  override def canEqual(other: Any): Boolean = other.isInstanceOf[Apple]

  override def equals(other: Any): Boolean = other match {
    case that: Apple =>
      super.equals(that) &&
        (that canEqual this) &&
        brand == that.brand &&
        color == that.color
    case _ => false
  }

  override def hashCode(): Int = {
    41 * (
      41 * (
        41 + super.hashCode
      ) + brand.hashCode
    ) + color.hashCode
  }
}

val a1 = new Apple("Fiji", "green")
val a2 = new Apple("Fiji", "green")

a1 == a2

a1.##
a2.##

case class Banana(brand: String, ripe: Boolean) extends Fruit("banana")

val b1 = Banana("Ffyfes", true)
val b2 = Banana("Ffyfes", true)

b1 == b2
b1.##
b2.##

