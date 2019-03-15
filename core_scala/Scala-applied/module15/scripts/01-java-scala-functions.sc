case class Person(name: String, age: Int)

import java.util.{Arrays, Comparator}

val javaArray = Array(
  Person("Harry", 25),
  Person("Sally", 22),
  Person("Tim", 33)
)

val comp1 = new Comparator[Person] {
  override def compare(o1: Person, o2: Person) = o1.age - o2.age
}

Arrays.sort(javaArray, comp1)

javaArray

Arrays.sort(javaArray, (p1: Person, p2: Person) => p2.age - p1.age)

javaArray
