import play.api.libs.json._

val fred = Json.parse("""{"name": "fred", "age": 20}""")

fred \ "name"
fred \ "age"

(fred \ "name").asOpt[String]
(fred \ "age").asOpt[Int]
(fred \ "age").asOpt[String]
(fred \ "rage").asOpt[Int]

val title = "I Ah to Thai"
val author = "E.R. Das"
val pages = 10
val year = 1992

val book = JsObject(Map(
  "title" -> JsString(title),
  "author" -> JsString(author),
  "pages" -> JsNumber(pages),
  "year" -> JsNumber(year)
))

// alternatively:
Json.parse(
  s"""{ "title": "$title", "author": "$author", "pages": $pages, "year": $year}"""
)


Json.prettyPrint(book)
book.toString

val nums = List(1,2,3,4,5)
Json.toJson(nums)

val words = List("nitwit", "wibble", "floobah")
Json.toJson(words)

Json.toJson(Map(
  "hi" -> 1,
  "yo" -> 2
))

// won't work - type classes must be provided for all types
/*Json.toJson(Map(
  "hi" -> 1,
  "yo" -> "two"
))*/

