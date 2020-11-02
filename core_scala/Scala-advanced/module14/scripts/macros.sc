import com.escalatesoft.macros._
import FunctionMacros._

// check that it works just like a normal function
val inc: Int => Int = (x: Int) => x + 1
val incNamed: Int => Int = new NamedFunction("((x: Int) => x + 1)", (x: Int) => x + 1)

val xs = List(1,2,3,4,5)

xs.map(inc)
xs.map(incNamed)



val f1 = (x: Int) => x + 1
val f2 = describeFn((x: Int) => x + 1)
val f3: Int => Int = describeFn((x: Int) => x + 1)

