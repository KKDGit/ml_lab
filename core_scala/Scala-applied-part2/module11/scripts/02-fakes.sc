trait DBAccess {
  def save[T](item: T): String
  def load[T](id: String): Option[T]
}

import java.util.UUID
import scala.util.Try

class FakeDBAccess extends DBAccess {
  private[this] var itemMap = Map.empty[String, Any]

  def save[T](item: T): String = {
    val uuid = UUID.randomUUID().toString
    itemMap = itemMap + (uuid -> item)
    uuid
  }

  def load[T](id: String): Option[T] = {
    Try {
      itemMap(id).asInstanceOf[T]
    }.toOption
  }
}

case class Person(name: String, age: Int)
val fake = new FakeDBAccess
val uuid = fake.save(Person("Sally", 23))
fake.load(uuid)
