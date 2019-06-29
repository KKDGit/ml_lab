var x = 1

while(x <= 5) {
  var y = 1
  while(y <= 5){
    println(s"$x times $y is ${x*y}")
    y += 1
  }
  x += 1
}

x = 1

while(x <= 5) {
  var y = 1
  while(y <= 5){
    val res = (x*y).toString
    if(res.contains('6')||res.contains('4'))
      //if(res.contains('6')|res.contains('4'))
      println(s"$x times $y is ${x*y}")
    y += 1
  }
  x += 1
}