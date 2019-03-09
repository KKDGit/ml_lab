val flag = true  // could be false...

if (flag) 1.0 else ()  // Double + Unit = AnyVal

if (flag) 1.0  // implicit Unit, Double + Unit = AnyVal

if (flag) "hi" // implicit Unit, String + Unit = Any

if (flag) "Hello" else null // String + Null = String

if (flag) 2.0 else null // Double + Null = Any

def fail(msg: String): Nothing =
  throw new IllegalStateException(msg)

if (flag) 2.0 else fail("not 2.0") // Double + Nothing = Double

if (flag) "yo" else fail("no yo") // String + Nothing = String

trait Fruit

case class Apple(name: String) extends Fruit
case class Orange(name: String) extends Fruit

if (true) Apple("Fiji") else Orange("Jaffa")
List(Apple("fiji"), Orange("Jaffa"))

trait Fruit1 extends Product with Serializable

case class Apple1(name: String) extends Fruit1
case class Orange1(name: String) extends Fruit1

if (true) Apple1("Fiji") else Orange1("Jaffa")
List(Apple1("fiji"), Orange1("Jaffa"))

trait Fruit2

case class Apple2(name: String) extends Fruit2
case class Orange2(name: String) extends Fruit2

val fruit: Fruit2 = if (true) Apple2("Fiji") else Orange2("Jaffa")
val fruits: List[Fruit2] = List(Apple2("fiji"), Orange2("Jaffa"))

