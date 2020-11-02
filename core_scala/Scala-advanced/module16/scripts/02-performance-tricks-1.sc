import com.escalatesoft.demo.XorRandom

def timeIt[A](name: String)(fn: => A): A = {
  val startTime = System.currentTimeMillis()
  val result = fn
  val elapsed = System.currentTimeMillis() - startTime
  println(s"$name took $elapsed ms")
  result
}


timeIt("x * 2") {
  var outer = 0
  while (outer < 5000000) {
    var x = 0
    while (x < 2000000) {
      val y = x * 2
      x += 1
    }
    outer += 1
  }
}

timeIt(" << 1") {
  var outer = 0
  while (outer < 5000000) {
    var x = 0
    while (x < 2000000) {
      val y = x << 1
      x += 1
    }
    outer += 1
  }
}

