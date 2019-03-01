implicit class TimesDo(val i: Int) //extends AnyVal
{
  def times(fn: => Unit): Unit = {
    for (_ <- 1 to i) fn
  }
}

5 times {
  println("hello")
}

