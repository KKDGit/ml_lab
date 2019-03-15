import scala.util.Try

case class Person(name: String, age: Int)
val p1 = Person("Gloria", 39)

Person.unapply(p1)


val webUrl = "http://www.escalatesoft.com/training/index.html"

object WebURL {
  def unapply(s: String): Option[(String, String, String)] = Try {
    val split1 = s.indexOf("://")
    val protocol = s.substring(0, split1)
    val rest = s.substring(split1 + 3)
    val split2 = rest.indexOf("/")
    val server = rest.substring(0, split2)
    val loc = rest.substring(split2 + 1)
    (protocol, server, loc)
  }.toOption

  def apply(fields: (String, String, String)): String = {
    val (proto, svr, loc) = fields
    s"$proto://$svr/$loc"
  }
}

WebURL.unapply(webUrl)

WebURL.apply("http", "www.escalatesoft.com", "training/index.html")

def splitUrl(s: String) = s match {
  case WebURL(proto, svr, loc) =>
    println(proto)
    println(svr)
    println(loc)
  case _ => println("no match")
}

splitUrl(webUrl)

splitUrl("https://www.google.com/images")

object WebURLSeq {
  def unapplySeq(s: String): Option[(String, String, Seq[String])] = Try {
    val split1 = s.split("://")
    val protocol = split1(0)
    val rest = split1(1).split("/").toList
    (protocol, rest.head, rest.tail)
  }.toOption

  def apply(fields: (String, String, Seq[String])): String = {
    val (proto, svr, locs) = fields
    s"""$proto://$svr/${locs.mkString("/")}"""
  }
}

val (protocol, server, locations) = WebURLSeq.unapplySeq(webUrl).get
WebURLSeq(protocol, server, locations)

def splitUrlSeq(s: String) = s match {
  case WebURLSeq(proto, svr, loc @ _*) =>
    println(proto)
    println(svr)
    println(loc)
  case _ => println("no match")
}

splitUrlSeq(webUrl)

splitUrlSeq("https://www.google.com/images")

val URLString = """^([^:]+.)://([^/]+)/(.+)?$""".r

val URLString(proto, site, rest) = "https://www.google.com/images"
val URLString(proto2, site2, rest2) = "http://www.something.com/"
