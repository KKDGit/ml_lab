import scala.util.control.NonFatal

case class DB(url: String, user: String, pass: String)
case class User(name: String, pass: String)
case class Session(id: String) {
  def query(q: String) = User("Dick", "password")
}

def withTransaction[A](db: DB)(fn: Session => A): A = {
  println(s"Starting session on DB ${db.url}")
  try {
    val a = fn(Session("test"))
    println(s"Committing transaction on DB ${db.url}")
    a
  }
  catch {
    case NonFatal(ex) =>
      println(s"Rolling back transaction on DB ${db.url}")
      throw(ex)
  }
}


object PostgresDBDetails {
  val dbURL: String = "jdbc:postgresql://localhost:5432/data"
  val dbUser: String = "user"
  val dbPass: String = "pass"
}

object PostgresDBConnection {
  import PostgresDBDetails._  // hard wired above
  def db = DB(dbURL, dbUser, dbPass)
}

class UserManagement {
  val db = PostgresDBConnection.db  // more hard wiring

  def findUser(id: Int): User =
    withTransaction(db) { implicit session =>
      session.query(s"select * from users where id = $id")
    }
}

(new UserManagement).findUser(123)