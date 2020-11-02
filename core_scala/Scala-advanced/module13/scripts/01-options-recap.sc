case class Address(street: String, city: String, state: String, zipCode: String)
case class Person(first: String, last: String, address: Option[Address])

def zipForPerson(op: Option[Person]): Option[String] =
  for {
    p <- op
    a <- p.address
  } yield a.zipCode

val people = List(
  Some(Person("Fred", "Bloggs", None)),
  Some(Person("Simon", "Jones",
    Some(Address("123 Main", "Fakesville", "AZ", "12345")))),
  None
)

people.map(zipForPerson)   // List(None, Some(12345), None)

people(0).flatMap(_.address).map(_.zipCode)  // None
people(1).flatMap(_.address).map(_.zipCode)  // Some(12345)
people(2).flatMap(_.address).map(_.zipCode)  // None