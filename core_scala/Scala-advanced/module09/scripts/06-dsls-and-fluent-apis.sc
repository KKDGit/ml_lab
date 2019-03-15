val or: String = "or"
val to: String = "to"

object To {
  def be(o: or.type) = this
  def not(t: to.type) = this
  def be: String = "That is the question"
}

To be or not to be

object TransactionType extends Enumeration {
  val Long, Atomic = Value
}

case class DBConnection(
  url: String,
  user: String = "postgres",
  pass: String = "password",
  txnType: TransactionType.Value = TransactionType.Atomic
)

DBConnection(url = "postgres:127.0.0.1/mydb")

DBConnection(
  url = "postgres:127.0.0.1/mydb",
  user = "dbUser",
  pass = "secret",
  txnType = TransactionType.Long
)