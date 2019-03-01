val s1: String = "hello"
val s2: String = null // no no no no no

val os1 = Option(s1)
val os2 = Option(s2)

os1.orNull
os2.orNull


val jl = new java.util.ArrayList[Int]
jl.add(1); jl.add(2); jl.add(3)

// will not compile
//jl.map(_ * 2)

import scala.collection.JavaConverters._

jl.asScala.map(_ * 2)


// Java method signature:
def someJavaFunc(xs: java.util.List[Integer]): java.util.List[Integer] = xs

val sl = List(1, 2, 3)

// Int is not an Integer!
//val r1 = someJavaFunc(sl.asJava)

val jl2 = sl.map( new java.lang.Integer(_) )  // explicitly box first
val r2 = someJavaFunc(jl2.asJava)
