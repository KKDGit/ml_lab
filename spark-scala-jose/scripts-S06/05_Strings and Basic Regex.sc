 println("hello")
 val fairwell = "Good" + "Bye"
 val repeat = "Dance!"*5
 val st1 = "hello"
 st1.length()
 val name = "Jose"
 val greet1 = s"Hello ${name}"
 printf("A string: %s , an integer %d, a float %f","hi",10,2.5)
 printf("A string: %s , an integer %d, a float %1.2f","hi",10,2.5)
 val greet2 = f"Hello ${name}"
 val greet = f"Hello $name"

 f"First letter of name is $name%.1s" //VERY VERY IMP
 s"First letter of name is $name%.1s" //VERY VERY IMP

 val st2 = "This is a long string"
 st2.charAt(0)
 st2.indexOf("a")
 val st3 = "term1 term2 term3"
 st3 contains "term1"
 st3 matches "term1"
 st3 matches "term1 term2 term3"
 val st = "hello"
 st slice (0,2)
 st slice (2,st.length)


//// STRINGS AND BASIC REGEX
//// Let's show some basic String functionality
//
//// Printing
//scala> println("hello")
//hello
//
//// Concatenation
//scala> val fairwell = "Good" + "Bye"
//fairwell: String = GoodBye
//
//// Repeating
//scala> val repeat = "Dance!"*5
//repeat: String = Dance!Dance!Dance!Dance!Dance!
//
//// String Length
//scala> val st = "hello"
//st: String = hello
//
//scala> st.length()
//res14: Int = 5
//
//// Inserting Objects
//scala> val name = "Jose"
//name: String = Jose
//// String Interploation
//scala> val greet = s"Hello ${name}"
//greet: String = Hello Jose
//// printf
//scala> printf("A string: %s , an integer %d, a float %f","hi",10,2.5)


//A string: hi , an integer 10, a float 2.500000
//
//scala> printf("A string: %s , an integer %d, a float %1.2f","hi",10,2.5)
//A string: hi , an integer 10, a float 2.50
//
//// 'f' Interploation
//scala> val greet = f"Hello ${name}"
//greet: String = Hello Jose
//
//scala> val greet = f"Hello $name"
//greet: String = Hello Jose
//
//// String Indexing
//scala> f"First letter of name is $name%.1s"
//res8: String = First letter of name is J
//
/////////////////////////////
///// Regular Expressions ///
/////////////////////////////
//
//// Index Location
//scala> val st = "This is a long string"
//st: String = This is a long string
//
//scala> st.charAt(0)
//res18: Char = T
//
//scala> st.indexOf("a")
//res19: Int = 8
//
//// Pattern matching
//scala> val st = "term1 term2 term3"
//st: String = term1 term2 term3
//
//scala> st contains "term1"
//res20: Boolean = true
//
//scala> st matches "term1"
//res11: Boolean = false
//
//scala> st matches "term1 term2 term3"
//res12: Boolean = true
//
//// Slicing
//scala> val st = "hello"
//st: String = hello
//
//scala> st slice (0,2)
//res2: String = he
//
//scala> st slice (2,st.length)
//res4: String = llo
