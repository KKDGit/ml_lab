trait JSONWrite[T] {
  def toJsonString(item: T): String
}

def jsonify[T: JSONWrite](item: T): String =
  implicitly[JSONWrite[T]].toJsonString(item)

implicit object StringJSONWrite extends JSONWrite[String] {
  def toJsonString(item: String) = s""""$item""""
}
jsonify("hello")

implicit object IntJsonWrite extends JSONWrite[Int] {
  def toJsonString(item: Int) = item.toString
}

implicit def listJsonWriter[T: JSONWrite]: JSONWrite[List[T]] =
  { (xs: List[T]) =>
    val tJson = implicitly[JSONWrite[T]]

    xs.map(tJson.toJsonString).mkString("[", ",", "]")
  }

jsonify(List(1,2,3))
jsonify(List("hello", "world"))

case class Person(name: String, age: Int)

import scala.reflect.runtime.universe._


abstract class CaseClassAbstractJsonWriter[T: TypeTag]
    extends JSONWrite[T] {

  val tt = typeTag[T]

  implicit val writer: JSONWrite[T] = this
}

abstract class CaseClassJsonWriter2[A: JSONWrite, B: JSONWrite, T: TypeTag]
    extends CaseClassAbstractJsonWriter[T] {

  def unapply(x: T): Option[(A, B)]
  private val aJson = implicitly[JSONWrite[A]]
  private val bJson = implicitly[JSONWrite[B]]

  private val name =
    this.getClass.getSimpleName.filterNot(_ == '$')

  private val fieldNames = tt.tpe.member(TermName("copy")).
    info.paramLists.flatMap(_.map(_.name.toString))

  private def fields(x: T): List[String] = unapply(x) match {
    case Some((a, b)) =>
      List(
        aJson.toJsonString(a),
        bJson.toJsonString(b)
      )
    case None => throw new IllegalStateException("Cannot serialize")
  }

  override def toJsonString(item: T): String = {
    val fieldPairs = fieldNames.zip(fields(item))

    val fieldStrings = for ((name, value) <- fieldPairs) yield {
      s""""$name": $value"""
    }

    val allFields = fieldStrings.mkString(", ")

    s""""{
       |  "$name": {$allFields}
       |}""".stripMargin
  }
}

implicit object Person extends CaseClassJsonWriter2[String, Int, Person]

val person = Person("Harry", 20)

jsonify(person)
