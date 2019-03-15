case class Rational(n: Int, d: Int) {
  require(d != 0)
  def this(n: Int) = this(n, 1) // auxiliary constructor
  override def toString = s"$n/$d"
  def +(that: Rational): Rational =
    Rational(
      n * that.d + that.n * d,
      d * that.d
    )
}

Rational(1, 2) + Rational(2, 3)


object Options {
  def zip[A, B](o1: Option[A], o2: Option[B]): Option[(A, B)] =
    (o1, o2) match {
      case (Some(a), Some(b)) => Some((a, b))
      case _ => None
    }

  def zip[A, B, C](o1: Option[A], o2: Option[B], o3: Option[C]): Option[(A, B, C)] =
    (o1, o2, o3) match {
      case (Some(a), Some(b), Some(c)) => Some((a,b,c))
      case _ => None
    }
}

Options.zip(Some(1), Some('a'), Some("hi"))
Options.zip(Some(1), None, Some("hi"))
