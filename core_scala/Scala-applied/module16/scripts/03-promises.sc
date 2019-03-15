import scala.concurrent._
import duration._

val promise = Promise[Int]

val future = promise.future

future.isCompleted
future.value

promise.success(10)

future.isCompleted
future.value

val promise2 = Promise[Int]

val future2 = promise2.future

promise2.failure(new IllegalStateException("oops"))

future2.isCompleted
future2.value

import java.util.concurrent.CompletableFuture
import java.util.function.Supplier

val supp = new Supplier[Int] {
  def get: Int = {
    Thread.sleep(500)
    10
  }
}
val cf = CompletableFuture.supplyAsync(supp)


/* def javaCFtoScalaF(cf: CompletableFuture[Int]): Future[Int] = {
  val promise = Promise[Int]
  cf.handle {
      (a: Int, ex: Throwable) => {
        Option(a).
          map(r => promise.success(r)).
          getOrElse(promise.failure(ex))
      }
    }

  promise.future
}

val sf = javaCFtoScalaF(cf)

sf.value
sf.isCompleted

Await.result(sf, 1.second)
*/

import scala.compat.java8.FutureConverters._

val sf2 = cf.toScala

Await.result(sf2, 1.second)
