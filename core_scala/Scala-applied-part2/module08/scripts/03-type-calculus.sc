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

trait Fruit extends Product with Serializable

case class Apple(name: String) extends Fruit
case class Orange(name: String) extends Fruit

if (true) Apple("Fiji") else Orange("Jaffa")
List(Apple("fiji"), Orange("Jaffa"))

