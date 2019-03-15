package com.escalatesoft.json.solution

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
object Person extends CaseClassJsonWriter3[String, String, Int, Person]



import scala.reflect.runtime.universe._

// OK - put your case class arity 3 serializer here, along with supporting
// abstract super-class

abstract class CaseClassAbstractJsonWriter[T: TypeTag]
  extends JSONWrite[T] {

  val tt = typeTag[T]

  implicit val writer: JSONWrite[T] = this
}

abstract class CaseClassJsonWriter3[A: JSONWrite, B: JSONWrite, C: JSONWrite, T: TypeTag]
  extends CaseClassAbstractJsonWriter[T] {

  def unapply(x: T): Option[(A, B, C)]
  private val aJson = implicitly[JSONWrite[A]]
  private val bJson = implicitly[JSONWrite[B]]
  private val cJson = implicitly[JSONWrite[C]]

  private val name =
    this.getClass.getSimpleName.filterNot(_ == '$')

  private val fieldNames = tt.tpe.member(TermName("copy")).
    info.paramLists.flatMap(_.map(_.name.toString))

  private def fields(x: T): List[String] = unapply(x) match {
    case Some((a, b, c)) =>
      List(
        aJson.toJsonString(a),
        bJson.toJsonString(b),
        cJson.toJsonString(c)
      )
    case None => throw new IllegalStateException("Cannot serialize")
  }

  override def toJsonString(item: T): String = {
    val fieldPairs = fieldNames.zip(fields(item))

    val fieldStrings = for ((name, value) <- fieldPairs) yield {
      s""""$name": $value"""
    }

    val allFields = fieldStrings.mkString(", ")

    s"""{
       |  "$name": {$allFields}
       |}""".stripMargin
  }
}

