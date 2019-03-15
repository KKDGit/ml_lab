abstract class Upper {
  def blip1: String
  def blip2: String
  val blop: String = "blop"
  def op(x: Int, y: Int): Int
}

class Lower extends Upper {
  override def blip1: String = "blip1"
  def blip2: String = "blip2"
  override val blop: String = "bloop"
  override def op(x: Int, y: Int): Int = x + y
  def op(x: Double, y: Double): Double = x + y
}
val lower = new Lower()

lower.blip1
lower.blip2
lower.blop
