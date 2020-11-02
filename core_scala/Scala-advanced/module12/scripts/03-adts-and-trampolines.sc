import scala.annotation.tailrec
sealed trait Bounce[A]
case class Done[A](result: A) extends Bounce[A]
case class Call[A](nextFunc: () => Bounce[A]) extends Bounce[A]

@tailrec
def trampoline[A](bounce: Bounce[A]): A =
  bounce match {
    case Call(nextFunc) => trampoline(nextFunc())
    case Done(x) => x
  }

object StillDaft {

  def even(n: Int): Bounce[Boolean] =
    n match {
      case 0 => Done(true)
      case x => Call(() => odd(x - 1))
    }

  def odd(n: Int): Bounce[Boolean] =
    n match {
      case 0 => Done(false)
      case x => Call(() => even(x - 1))
    }
}

StillDaft.even(6)

trampoline(StillDaft.even(6))
trampoline(StillDaft.odd(6))
trampoline(StillDaft.odd(9999999))