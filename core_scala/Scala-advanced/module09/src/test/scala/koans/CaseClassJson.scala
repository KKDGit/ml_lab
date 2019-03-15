package koans

trait JSONWrite[T] {
  def toJsonString(item: T): String
}

object JSONWrite {

  def jsonify[T: JSONWrite](item: T): String =
    implicitly[JSONWrite[T]].toJsonString(item)

  implicit object StringJSONWrite extends JSONWrite[String] {
    def toJsonString(item: String) = s""""$item""""
  }

  implicit object IntJsonWrite extends JSONWrite[Int] {
    def toJsonString(item: Int) = item.toString
  }
}


// here's our Arity 3 case class
case class Person(first: String, last: String, age: Int)

// once you have written your implementation of CaseClassJsonWriter3, uncomment the following to provide the type class
// object Person extends CaseClassJsonWriter3[String, String, Int, Person]



import scala.reflect.runtime.universe._

// OK - put your case class arity 3 serializer here, along with supporting
// abstract super-class


