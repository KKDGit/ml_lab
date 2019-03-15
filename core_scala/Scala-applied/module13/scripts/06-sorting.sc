case class Person(name: String, age: Int)

val xs = List(Person("Harry", 25), Person("Sally", 23), Person("Fred", 31))

xs.sortWith((p1, p2) => p1.age < p2.age)
xs.sortBy(_.name)

List(5, 2, 3, 4, 8, 1, 7).sorted

// will not work without an implicit ordering
// xs.sorted

implicit object PersonOrdering extends Ordering[Person] {
  override def compare(x: Person, y: Person) = {
    if (x.name == y.name) x.age - y.age
    else if (x.name > y.name) 1 else -1
  }
}

xs.sorted
