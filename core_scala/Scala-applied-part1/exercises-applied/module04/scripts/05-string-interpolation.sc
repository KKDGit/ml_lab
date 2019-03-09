val x = 10

val y = 2.12

val name = "Fred"

s"$name $x $y"

s"$name is ${x * y}"

f"$name is ${x * y}%09.4f"

//s"$names"
s"${name}s"

"\thi\nho"
raw"\thi\nho"

"""\thi\nho"""

val a1 = """ kranthi "kumar"
  |dosapati
""".stripMargin

val a2 = s""" kranthi "kumar" $name
           |dosapati
         """.stripMargin

val a3 = s""" kranthi "kumar" $name dosapati """

a1
a2
