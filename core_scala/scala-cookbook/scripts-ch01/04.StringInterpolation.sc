//1.4. Substituting Variables into Strings

println("1.4. Substituting Variables into Strings")

 val name = "Fred"
 val age = 33
 val weight = 200.00
 println(s"$name is $age years old, and weighs $weight pounds.")
 println(s"Age next year: ${age + 1}")
 println(s"You are 33 years old: ${age == 33}")


 case class Student(name: String, score: Int)
 val hannah = Student("Hannah", 95)
 println(s"${hannah.name} has a score of ${hannah.score}")

// error: this is intentionally wrong
 println(s"$hannah.name has a score of $hannah.score")

 println(f"$name is $age years old, and weighs $weight%.2f pounds.")
 println(f"$name is $age years old, and weighs $weight%.0f pounds.")
 val out = f"$name, you weigh $weight%.0f pounds."

 s"foo\nbar"
 raw"foo\nbar"

println("-------------Old Days-----------")
val name1 = "Fred"
val age1 = 33

val s2 = "%s is %d years old".format(name1, age1)
println("%s is %d years old".format(name1, age1))

/*
%c Character
%d Decimal number (integer, base 10)
%e Exponential floating-point number
%f Floating-point number
%i Integer (base 10)
%o Octal number (base 8)
%s A string of characters
%u Unsigned decimal (integer) number
%x Hexadecimal number (base 16)
%% Print a “percent” character
\% Print a “percent” character

*/