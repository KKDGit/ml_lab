case class Address(street: String, city: String, postCode: Option[String])
case class Person(name: String, phone: Option[String], address: Option[Address])

val harry = Person("Harry", None, Some(Address(
  "123 Little Whinging way", "Purley", Some("PN22 6RT")
)))

val sally = Person("Sally", Some("321-222-3344"), None)

harry
sally

sally == harry
sally == sally
sally == Person("Sally", Some("321-222-3344"), None)
sally == Person("Sally", Some("321-234-3344"), None)

sally.hashCode
Person("Sally", Some("321-222-3344"), None).hashCode
harry.hashCode

harry.name
harry.address.map(_.city)
harry.phone
sally.phone


val sally2 = sally.copy(address = harry.address, phone = Some("321-333-2211"))
val harry2 = harry.copy(phone = sally2.phone)


def postCodeForHarry(person: Person) = person match {
  case Person("Harry", _, Some(Address(street, city, Some(postcode)))) =>
    println("Harry found with postcode")
    println(s"City $city")
    println(s"Street $street")
    postcode
  case _ =>
    println("Match not made")
    ""
}

postCodeForHarry(harry)
postCodeForHarry(harry2)
postCodeForHarry(sally)
postCodeForHarry(sally2)
