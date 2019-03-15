import play.api.libs.json._

case class Person(first: String, last: String, age: Int)

object Person {
  implicit val personFormat: Format[Person] = new Format[Person] {
    def reads(json: JsValue): JsResult[Person] = {
      val first = (json \ "first").as[String]
      val last = (json \ "last").as[String]
      val age = (json \ "age").as[Int]
      JsSuccess(Person(first, last, age))
    }

    def writes(o: Person): JsValue = {
      val itemsMap = Map(
        "first" -> JsString(o.first),
        "last" -> JsString(o.last),
        "age" -> JsNumber(o.age)
      )
      JsObject(itemsMap)
    }
  }
}


val p1 = Person("Harry", "Potter", 22)
val p2 = Person("Sally", "James", 23)

val people = Map(
  "harry" -> p1,
  "sally" -> p2
)

val peopleStr = Json.prettyPrint(Json.toJson(people))
val items = Json.parse(peopleStr).as[Map[String, Person]]

import play.api.libs.functional.syntax._

case class Car(make: String, model: String, year: Int)

val cars = Seq(
  Car("Ford", "Mustang", 1965),
  Car("Honda", "S2000", 2002)
)

object Car {
  implicit val carFormat: Format[Car] = (
    (JsPath \ "make").format[String] and
      (JsPath \ "model").format[String] and
      (JsPath \ "year").format[Int]
    )(Car.apply, unlift(Car.unapply))

  // or, the macro way...
  //implicit val carFormat: Format[Car] = Json.format[Car]
}

val carsStr = Json.prettyPrint(Json.toJson(cars))

val carsAgain = Json.parse(carsStr).as[Seq[Car]]

val carsJson = Json.parse(carsStr)

for (make <- carsJson \\ "make") println(make.as[String])

