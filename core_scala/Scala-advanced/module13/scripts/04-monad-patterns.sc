import cats.Id
import cats.data.{Kleisli, Reader, State}
import cats.effect.IO

import scala.collection.immutable.Queue
import scala.io.StdIn

val program = IO {
  println("Well, hello, who are you?")
  val name = StdIn.readLine()
  println(s"Well hello, $name, how old are you?")
  val age = StdIn.readLine().trim.toInt
  println(s"Hi $name, I remember being $age, it was grand...")
}

// needs to be run in a shell because of IO
// program.unsafeRunSync()

// and they compose
val p1 = IO {
  println("Name?")
  StdIn.readLine()
}

val p2 = IO {
  println("Age?")
  StdIn.readLine().trim.toInt
}

case class Person(name: String, age: Int)

val p3 = for {
  name <- p1
  age <- p2
} yield Person(name, age)

// again - need to run it in the shell
// p3.unsafeRunSync()


case class User(name: String, sessionId: String)

case class DBConn(name: String) {
  def getUser(id: Int): User = User("Dick", "1234")
  def putUser(user: User): Unit = println(s"Updating $user")
}

object UserList {
  def updateUser(id: Int, newName: String) =
    Reader { (db: DBConn) =>
      val user = db.getUser(123)
      val altered = user.copy(name = newName)
      db.putUser(altered)
    }
}

val updateU = UserList.updateUser(123, "Ricardo")

updateU.run(DBConn("mydb"))


// Writer monad

import cats.data.Writer

def authorized(name: String) =
  Writer(List("Verifying user"), name.length >= 3)

def greet(name: String, loggedIn: Boolean) =
  Writer(List("Greet user by name"), {
    val userName = if(loggedIn) name else "User"
    s"Hello $userName"
  })

import cats.instances.list._
val name = "Dick"

val result: Writer[List[String], String] = for {
  loggedIn <- authorized(name)
  greeting <- greet(name, loggedIn)
} yield greeting

result.run
// (List(Verifying user, Greet user by name),Hello Dick)


// State Monad

val take = State[Queue[Int], Int] {
  case Queue()      => sys.error("queue is empty")
  case q            => (q.init, q.last)
}

def put(a: Int) = State[Queue[Int], Unit] { q =>
  (a +: q, ())
}

def useQueue: State[Queue[Int], (Int, Int)] = for {
  _ <- put(3)
  a <- take
  b <- take
} yield (a, b)

useQueue.run(Queue(5, 8, 2, 1)).value
