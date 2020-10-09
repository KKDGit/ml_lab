/////////////////implicits R & D
def call(str: String): Unit = println("hi "+ str)
implicit def intToString(i: Int): String = i.toString
call(1)



implicit class RichString(str: String) {
  def awesomeMethod(): Unit =
    println(s"awesomeMethod for $str")
}
"string".awesomeMethod()

implicit def richString(str: String) = new {
  def awesomeMethod1(): Unit =
    println(s"awesomeMethod1 for $str")
}

"string".awesomeMethod1()

case class Task(task_id:Int)
case class Executor(exe_id:Int) {
  def run(task:Task) = println(s"running task: ${task.task_id}, ${this.exe_id}")
}

implicit val executor: Executor = Executor(999)

def run(task: Task)(implicit executor: Executor): Unit =
  executor.run(task)

run(Task(1))



trait Equal[A] {
  def equal(a1: A, a2: A): Boolean
}

object Equal {
  def apply[A](implicit instance: Equal[A]): Equal[A] =
    instance

//  implicit class EqualSyntax[A](a: A) {
//    def equal(that: A)(implicit e: Equal[A]): Boolean =
//      e.equal(a, that)
//  }
}

implicit val intEqual: Equal[Int] =
  (a1: Int, a2: Int) => a1 == a2

println(Equal[Int].equal(1, 2))
println(Equal[Int].equal(1, 1))




trait Equal1[A] {
  def equal(a1: A, a2: A): Boolean
}

object Equal1 {
  def apply[A](implicit instance: Equal1[A]): Equal1[A] =
    instance
    implicit class EqualSyntax[A](a: A) {
      def equal(that: A)(implicit e: Equal1[A]): Boolean =
        e.equal(a, that)
    }
}
implicit val intEqual1: Equal1[Int] =
  (a1: Int, a2: Int) => a1 == a2

import Equal1.EqualSyntax
println(1 equal 2)
println(1 equal 1)