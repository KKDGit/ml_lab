sealed class AccountType
case object Checking extends AccountType
case object Savings extends AccountType


def checking(at: AccountType): Boolean = at match {
  case Checking => true
}

val xs = List(1,2,3)

def matchList(l: List[Int]): String = {
  l match {
    case a :: rest => "it's a non empty list"
  }
}