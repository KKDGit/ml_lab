import com.escalatesoft.demo.XorRandom

import scala.annotation.tailrec
val random = XorRandom.random()

def timeIt[A](name: String)(fn: => A): A = {
  val startTime = System.currentTimeMillis()
  val result = fn
  val elapsed = System.currentTimeMillis() - startTime
  println(s"$name took $elapsed ms")
  result
}

// While loops (and tail rec) are much faster than for expressions

var sum: Int = 0

timeIt("random mean with for") {
  for (i <- 0 to 100000000) {
    sum = sum + (if ((i & 1) == 0) i else -i)
  }
}
println(sum)

sum = 0
timeIt("random mean with while") {
  var i = 0

  while (i < 100000000) {
    sum = sum + (if ((i & 1) == 0) i else -i)
    i += 1
  }
}
println(sum)

@tailrec
def randomMean(limit: Int, ct: Int = 0, sum: Int = 0): Double = {
  if (ct >= limit) sum else randomMean(limit, ct + 1,
    sum + (if ((ct & 1) == 0) ct else -ct))
}

timeIt("random mean with tailrec") {
  randomMean(100000000)
}

// also square roots

case class Coords(x: Double, y: Double)

def withinDistanceOf(compare: Coords, x: Double, y: Double, distance: Double): Boolean = {
  val xDelta = compare.x - x
  val yDelta = compare.y - y
  val dist = math.sqrt(xDelta * xDelta + yDelta * yDelta)
  dist <= distance
}

def withinDistanceOf2(compare: Coords, x: Double, y: Double,
  distanceSquared: Double): Boolean = {
  val xDelta = compare.x - x
  val yDelta = compare.y - y
  (xDelta * xDelta + yDelta * yDelta) <= distanceSquared
}

var countWithinDistance = 0


var i = 0
val comparisonsCount = 100000000
val compare = Coords(2.0, 2.0)

timeIt("Count close items") {
  while (i < comparisonsCount) {
    val x = random.nextDouble() * 10.0
    val y = random.nextDouble() * 10.0
    if (withinDistanceOf2(compare, x, y, 4.0)) countWithinDistance += 1

    i += 1
  }
}

println(countWithinDistance)


