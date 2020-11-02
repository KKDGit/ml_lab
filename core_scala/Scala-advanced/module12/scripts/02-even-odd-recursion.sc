// why would you ever do this?
object Daft {
  def odd(n: Int): Boolean =
    n match {
      case 0 => false
      case x => even(x - 1)
    }

  def even(n: Int): Boolean =
    n match {
      case 0 => true
      case x => odd(x - 1)
    }
}

Daft.odd(6)
Daft.even(6)
Daft.odd(999999)