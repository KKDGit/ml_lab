val numbersMap = Map(1 -> "one", 2 -> "two", 3 -> "three")

for ((k, v) <- numbersMap) {
  println(s"$k is $v")
}

case class Address(street: String, city: String, postCode: Option[String])
case class Person(name: String, phone: Option[String], address: Option[Address])

val harry = Person("Harry", None, Some(Address(
  "123 Little Whinging way", "Purley", Some("PN22 6RT")
)))

val sally = Person("Sally", Some("321-222-3344"), None)

val sally2 = sally.copy(address = harry.address, phone = Some("321-333-2211"))
val harry2 = harry.copy(phone = sally2.phone)

val Person(name, phone, Some(Address(_, _, postCode))) = harry
// but match error below
// val Person(name2, phone2, Some(Address(_, _, postCode2))) = sally


val people = List(harry, harry2, sally, sally2)

for {
  Person(name, phone, _) <- people
  if phone.isDefined
} yield name -> phone.get


// pattern matches as partial functions

numbersMap.map {
  case (1, w) => s"It's 1 and the word is $w"
  case (k, v) => s"Not 1 but ($k, $v)"
}

