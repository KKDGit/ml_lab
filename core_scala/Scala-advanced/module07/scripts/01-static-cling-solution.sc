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


class PostgresDBDetails(val dbURL:String, val dbUser:String, val dbPass:String)


class PostgresDBConnection(dbDetails:PostgresDBDetails) {
  import dbDetails._
  def db = DB(dbURL, dbUser, dbPass)
}

class UserManagement(db:DB) {
  def findUser(id: Int): User =
    withTransaction(db) { implicit session =>
      session.query(s"select * from users where id = $id")
    }
}

val dbDetails = new PostgresDBDetails("url", "user", "pass")
val dbConn = new PostgresDBConnection(dbDetails)
(new UserManagement(dbConn.db)).findUser(123)
