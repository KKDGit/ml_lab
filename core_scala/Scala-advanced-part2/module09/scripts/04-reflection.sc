val s = "hello"

s.charAt(1)

val a: Any = s

// The traditional way
val charAt = a.getClass.getMethod("charAt", classOf[Int])
charAt.invoke(a, new Integer(1)).asInstanceOf[Char]


// The scala way:
a.asInstanceOf[{def charAt(i: Int): Char}].charAt(1)

