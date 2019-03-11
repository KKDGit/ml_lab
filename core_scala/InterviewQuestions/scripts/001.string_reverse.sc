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
//@tailrec //throws errors as recursive call is NOT in tail position
def reverse3(s: String): String = {
  val len = s.length
  if (len == 1) {
    s
  } else {
    reverse3(s.substring(1, len)) + s.charAt(0)
  }
}


// recursively, with tail recursion
//@tailrec //throws errors as recursive call is NOT in tail position
def reverse4(s: String): String = {
  val len = s.length
  if (len == 1) {
    s
  } else {
    s.substring(len-1, len) + reverse4(s.substring(0, len-1))
  }
}

println("------------------------------------------------")

reverse1(reverse1("Kranthi Kumar"))
reverse2(reverse2("Hi THERE"))
reverse3(reverse3("WHat is this"))
reverse4(reverse4("Hi How are You"))