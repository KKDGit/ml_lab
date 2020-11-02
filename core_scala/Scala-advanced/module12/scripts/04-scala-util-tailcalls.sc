import scala.util.control.TailCalls._
object StillDaft extends TailRec[Boolean] {
  def even(n: Int): TailRec[Boolean] =
    n match {
      case 0 => done(true)
      case x => tailcall(odd(x - 1) )
    }
  def odd(n: Int): TailRec[Boolean] =
    n match {
      case 0 => done(false)
      case x => tailcall(even(x - 1))
    }
}

StillDaft.even(6)

StillDaft.even(6).result
StillDaft.odd(6).result
StillDaft.odd(9999999).result