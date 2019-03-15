implicit class TimesDo(n: Int) {
  def times(fn: => Unit): Unit = {
    for (_ <- 1 to n) fn
  }
}

5 times {
  println("hello")
}
