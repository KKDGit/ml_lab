case class Address(street: String, city: String,
                   postCode: Option[String])
case class Person(name: String, phone: Option[String],
                  address: Option[Address])

val harry = Person("Harry", None, Some(Address(
  "123 Little Whinging way", "Purley", Some("PN22 6RT")
)))

val sally = Person("Sally", Some("321-222-3344"), None)

val sally2 = sally.copy(address = harry.address,
  phone = Some("321-333-2211"))
val harry2 = harry.copy(phone = sally2.phone)

val people = List(harry, harry2, sally, sally2)

for {
  Person(_, phone, _) <- people
} yield phone.getOrElse("XXX-XXX-XXXX")


for {
  Person(_, phone, _) <- people
  if phone.isDefined
} yield phone.get

//get will throw exception is there is None...
//before that isDefined check is necessary

val phones: List[String] = people.flatMap(_.phone)
val phones2: List[String] = people.
  map(_.phone.getOrElse("XXXX"))
