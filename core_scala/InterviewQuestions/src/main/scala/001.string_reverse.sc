def reverse1(s: String): String = {
  s.reverse
}

// iterating backward through the string
def reverse2(s: String): String = {
  val buf = new StringBuilder
  val len = s.length
  for (i <- 0 until len) {
    buf.append(s.charAt(len - i - 1))
  }
  buf.toString
}

// recursively
def reverse3(s: String): String = {
  val len = s.length
  if (len == 1) {
    s
  } else {
    reverse3(s.substring(1, len)) + s.charAt(0)
  }
}

// recursively, with tail recursion
def reverse4(s: String): String = {
  val len = s.length
  if (len == 1) {
    s
  } else {
    s.substring(len-1, len) + reverse4(s.substring(0, len-1))
  }
}