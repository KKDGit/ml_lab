class CoordsC(val x: Double, val y: Double) {
  override def toString: String = s"($x, $y)"
  val distToOrigin: Double = math.sqrt((x * x) + (y * y))
}

val c1 = new CoordsC(3.0, 4.0)
c1.distToOrigin

// will not compile, traits can't have constructor params
//trait CoordsT(x: Double, y: Double) {
//  override def toString: String = s"($x, $y)"
//}

trait CoordsT {
  val x: Double
  val y: Double
  override def toString: String = s"($x, $y)"
  lazy val distToOrigin: Double = math.sqrt((x * x) + (y * y))
}

case class Coords(x: Double, y: Double) extends CoordsT

val c2 = Coords(3.0, 4.0)
c2.distToOrigin


val c3 = new CoordsT {
  val x: Double = 3.0
  val y: Double = 4.0
}

c3.distToOrigin

val c4 = new {
  val x: Double = 3.0
  val y: Double = 4.0
} with CoordsT

c4.distToOrigin