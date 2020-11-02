sealed abstract class Optional[+T] {
  def isDefined: Boolean
  def map[U](fn: T => U): Optional[U]		         // Functor
  def flatMap[U](fn: T => Optional[U]): Optional[U]  // Monad* (also apply)
  def withFilter(p: T => Boolean): Optional[T]
}

case object Nada extends Optional[Nothing] {
  val isDefined: Boolean = false
  def map[U](fn: Nothing => U): Optional[U] = Nada
  def flatMap[U](fn: Nothing => Optional[U]): Optional[U] = Nada
  def withFilter(p: Nothing => Boolean): Optional[Nothing] = Nada
}

case class Item[+T](value: T) extends Optional[T] {
  val isDefined: Boolean = true
  def map[U](fn: T => U): Optional[U] = Item(fn(value))
  def flatMap[U](fn: T => Optional[U]): Optional[U] = fn(value)
  def withFilter(p: T => Boolean): Optional[T] =
    if (p(value)) this else Nada
}

case class Address(street: String, city: String, state: String, zipCode: String)
case class Person(first: String, last: String, address: Optional[Address])

def zipForPerson(startsWith: String)(op: Optional[Person]) =
  for {
    p <- op
    if p.first.startsWith(startsWith)
    a <- p.address
  } yield a.zipCode

val people = List(
  Item(Person("Fred", "Bloggs", Nada)),
  Item(Person("Simon", "Jones",
    Item(Address("123 Main", "Fakesville", "AZ", "12345")))),
  Nada
)

people.map(zipForPerson("S"))
people.map(zipForPerson("D"))

// Functor Laws
Item(10).map(identity) == Item(10)
Nada.map(identity) == Nada
Item(10).map(x => x * 2 + 3) == Item(10).map(_ * 2).map(_ + 3)

// Monad Laws
Item(5).flatMap (x => Item(x * 2)) == Item(10)
Item(5).flatMap(Item.apply) == Item(5)
(Item(5).flatMap (x => Item(x*2))).flatMap (y => Item(y+3)) ==
  Item(5).flatMap (x => Item(x*2).flatMap (y => Item(y+3)))