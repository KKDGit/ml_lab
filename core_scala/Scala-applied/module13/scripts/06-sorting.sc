case class Person(name: String, age: Int)

val xs = List(Person("Harry", 25),
  Person("Sally", 23),
  Person("Fred", 31))

xs.sortWith((p1, p2) => p1.age < p2.age)
xs.sortBy(_.age)
xs.sortWith((p1, p2) => p1.age > p2.age)
xs.sortBy(_.name)

List(5, 2, 3, 4, 8, 1, 7).sorted

// will not work without an implicit ordering
// xs.sorted


val persons = List(
  Person("Kranthi", 31),
  Person("Harry", 25),
  Person("Sally", 23),
  Person("Fred", 31),
  Person("Abc", 38),
  Person("Abc", 21),
  Person("Cat", 31),
  Person("Zbc", 38),
  Person("Kranthi", 38))


implicit object PersonOrdering extends Ordering[Person] {
  override def compare(x: Person, y: Person): Int = {
    if (x.age == y.age) {if (x.name > y.name) 1 else -1}
    else if (x.age < y.age) 1 else -1
  }//by age desc and then by name asc
}
implicit object PersonOrdering1 extends Ordering[Person] {
  override def compare(x: Person, y: Person): Int = {
    if (x.name == y.name) x.age - y.age
    else if (x.name < y.name) 1 else -1
  }//by name desc and then by age asc
}
implicit object PersonOrdering2 extends Ordering[Person] {
  override def compare(x: Person, y: Person): Int = {
    if(x.age < y.age) 1 else -1
  }//by age desc but if age is same it retains order from last
}

persons
persons.sorted(PersonOrdering)
//by age desc and then by name asc
persons.sorted(PersonOrdering1)
//by name desc and then by age asc
persons.sorted(PersonOrdering2)
//by age desc but if age is same it retains order from last
