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

trait DBConnection{val db:DB}

class PostgresDBConnection(val dbURL:String,
                           val dbUser:String,
                           val dbPass:String)
  extends DBConnection {
  val db = DB(dbURL, dbUser, dbPass)
}

class UserManagement(dbConn:DBConnection) {
  val db = dbConn.db
  def findUser(id: Int): User =
    withTransaction(db) { implicit session =>
      session.query(s"select * from users where id = $id")
    }
}

val dbConn = new PostgresDBConnection("url", "user", "pass")
new UserManagement(dbConn).findUser(123)
