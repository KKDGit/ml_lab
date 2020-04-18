def fakeWeatherLookup(wxCode: String) = {
	Thread.sleep(5000)
	wxCode.toList.map(_.toInt).sum / 10.0
}

fakeWeatherLookup("PDX")
fakeWeatherLookup("SFO")
fakeWeatherLookup("NDX")
fakeWeatherLookup("PDX")

import com.google.common.cache.{CacheLoader, CacheBuilder}
import scala.concurrent._
import duration._
import ExecutionContext.Implicits.global


object FakeWeatherLookup {
	private val cache = CacheBuilder.newBuilder().
		build {
			new CacheLoader[String, Future[Double]] {
				def load(key: String) = Future(fakeWeatherLookup(key))
			}
		}

	def apply(wxCode: String): Future[Double] =
		cache.get(wxCode)
}

val f1 = FakeWeatherLookup("SFO")
val f2 = FakeWeatherLookup("SFO")
val f3 = FakeWeatherLookup("SFO")
val f4 = FakeWeatherLookup("RXFACT")
val f5 = FakeWeatherLookup("RXFACT")

Await.result(f1, 10.seconds)
Await.result(f2, 10.seconds)
Await.result(f3, 10.seconds)
Await.result(f4, 10.seconds)
Await.result(f5, 10.seconds)
