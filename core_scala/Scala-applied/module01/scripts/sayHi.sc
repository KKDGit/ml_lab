var x = 0

val args = "Kranthi" :: "Kumar" :: "D" :: Nil

while (x < args.length) {
  println(s"Hi ${args(x)}")
  x = x + 1
}