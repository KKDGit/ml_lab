val s1: String = "Hello"
val s2: String = null

val l1: Int = s1.length  // fine
//val l2: Int = s2.length  // oops - runtime error

val os1: Option[String] = Some("Hello")
val os2: Option[String] = None

val ol1: Option[Int] = os1.map(_.length)
val ol2: Option[Int] = os2.map(_.length)


case class Address(street: String, city: String, state: String, zipCode: String)
case class Person(first: String, last: String, address: Option[Address])

val p1 = Some(Person("Fred", "Bloggs", None))
val p2 = Some(Person("Simon", "Jones",
  Some(Address("123 Main", "Fakesville", "AZ", "12345"))))
val p3: Option[Person] = None

for (p <- p1; a <- p.address) yield a.zipCode

for (p <- p2; a <- p.address) yield a.zipCode

for (p <- p3; a <- p.address) yield a.zipCode

p2.flatMap(_.address.map(_.zipCode))


val optPeople = Some(Seq(p1, p2, p3))

for {
  people <- optPeople.toSeq
  personOpt <- people
  person <- personOpt.toSeq
  address <- person.address.toSeq
} yield address.zipCode

