trait CompareAge[T] {
  def older(item: T): T
}

def getOlder[T <: CompareAge[T]](item1: T, item2: T): T = {
  item1 older item2
}


case class VintageCar(make: String, model: String, year: Int)
  extends CompareAge[VintageCar] {

  def older(other: VintageCar): VintageCar =
    if (this.year < other.year) this else other
}

getOlder(
  VintageCar("Ford", "Mustang", 1965),
  VintageCar("Ford", "Model T", 1922))

case class Person(name: String, age: Int) extends CompareAge[Person] {
  override def older(other: Person) =
    if (other.age > this.age) other else this
}

getOlder(Person("Fred", 25), Person("Jill", 28))

val people = List(Person("Fred", 25), Person("Jill", 28), Person("Sally", 22))

// compile error, until we define an Ordering[Person]
// people.sorted

implicit object PersonOrdering extends Ordering[Person] {
  override def compare(x: Person, y: Person) = x.age - y.age
}

people.sorted