//1.2. Creating Multiline Strings
println("------1.2. Creating Multiline Strings-------")
val foo = """This is
    a multiline
    String"""

val speech = """Four score and
               |seven years ago""".stripMargin

val speecha = """Four score and
    #seven years ago""".stripMargin('#')

val speechb = """This is known as a
                |"multiline" string
                |or 'heredoc' syntax.""". stripMargin.replaceAll("\n", " ")

