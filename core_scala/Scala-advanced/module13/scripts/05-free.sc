import cats.free.Free

import scala.util.Try
// Free

sealed trait DBFree[A]
case class Save[T](id: Int, value: T) extends DBFree[Unit]
case class Load[T](id: Int) extends DBFree[Option[T]]
case class Remove(id: Int) extends DBFree[Boolean]
case object ShowState extends DBFree[Unit]

type DB[A] = Free[DBFree, A]

// lifters, optional but make life easier
import cats.free.Free.liftF

def save[T](id: Int, value: T): DB[Unit] =
  liftF[DBFree, Unit](Save[T](id, value))

def load[T](id: Int): DB[Option[T]] =
  liftF[DBFree, Option[T]](Load[T](id))

def delete(id: Int): DB[Boolean] =
  liftF[DBFree, Boolean](Remove(id))

def showState(): DB[Unit] =
  liftF[DBFree, Unit](ShowState)

def modify[T](id: Int, fn: T => T): DB[Unit] =
  for {
    vOpt       <- load[T](id)
    updatedOpt =  vOpt.map(v => fn(v))
    _          <- updatedOpt.
                    map(updated => save[T](id, updated)).
                    getOrElse(Free.pure(()))
  } yield ()


case class Person(name: String, age: Int)

def program: DB[Option[Person]] =
  for {
    _ <- showState()
    _ <- save(123, Person("Dick", 21))
    _ <- showState()
    _ <- modify[Person](123, _.copy(age = 22))
    _ <- showState()
    _ <- save(124, Person("Joan", 22))
    _ <- showState()
    p <- load[Person](123)
    _ <- delete(124)
    _ <- showState()
  } yield p


import cats.{Id, ~>}
import scala.collection.mutable

def interpreter: DBFree ~> Id  = new (DBFree ~> Id) {
    val fakeDB = mutable.Map.empty[Int, Any]

    def apply[A](fa: DBFree[A]): Id[A] =
      fa match {
        case Save(id, value) =>
          fakeDB(id) = value
          ()
        case Load(id) =>
          fakeDB.get(id).flatMap(x => Try(x.asInstanceOf[A]).toOption)
        case Remove(id) =>
          fakeDB.remove(id).isDefined
        case ShowState =>
          println("State now ----------------")
          println(fakeDB)
          ()
      }
  }

val result: Option[Person] = program.foldMap(interpreter)
