import scala.reflect.runtime.universe._

val fnTree = q"(x: Int) => x + 1"

showCode(fnTree)

val aVal = q"val x = 10"

aVal match {
  case q"val x = $v" => s"The value is $v"
}

val aSeq = q"Seq(1,2,3)"

aSeq match {
  case q"Seq(..$x)" => x
}

class Animal
trait HasLegs
trait Reptile

val cd =
  q"""class Frog extends Animal with HasLegs with Reptile {
      val color: String = "green"
  }
   """

val q"class $c extends $s with ..$t {..$b}" = cd
