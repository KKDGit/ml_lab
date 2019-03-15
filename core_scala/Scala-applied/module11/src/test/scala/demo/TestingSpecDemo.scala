package demo

import org.scalamock.scalatest.MockFactory
import org.scalatest._
import org.scalatest.prop.PropertyChecks

class TestingSpecDemo extends FunSpec with Matchers with PropertyChecks with MockFactory {

  case class Weather(temp: Double, precip: Double, pressure: Double)

  trait WeatherService {
    def getWeather(icaoCode: String): Weather
    def operational(): Boolean
  }

  def lookupWeather(weatherService: WeatherService, icaoCode: String): Option[Weather] = {
    if (!weatherService.operational()) None else Some(weatherService.getWeather(icaoCode))
  }

  describe ("Retrieving the weather from the weather service") {
    it ("should call getWeather if operational is true") {
      val mockWS = mock[WeatherService]

      (mockWS.operational _: () => Boolean).expects().returning(true)
      (mockWS.getWeather _).expects("PDX").returning(Weather(55.0, 0.0, 1012.0))

      val weather = lookupWeather(mockWS, "PDX")

      val tolerance = 1e-6

      weather should be (defined)
      weather.get.precip should be (0.0 +- tolerance)
      weather.get.pressure should be (1012.0 +- tolerance)
      weather.get.temp should be (55.0 +- tolerance)
    }

    it ("should not call the getWeather if service not operational") {
      val mockWS = mock[WeatherService]

      (mockWS.operational _: () => Boolean).expects().returning(false)

      val weather = lookupWeather(mockWS, "PDX")

      weather should be (empty)
    }

    it ("should not call the getWeather if operational throws an exception") {
      val mockWS = mock[WeatherService]

      (mockWS.operational _: () => Boolean).expects().throwing(new IllegalStateException("network failure"))

      val ex = intercept[IllegalStateException] {
        lookupWeather(mockWS, "PDX")
      }

       ex.getMessage should be ("network failure")
    }

    it ("should call the lookup weather with different codes when necessary") {
      val stubWS = stub[WeatherService]

      (stubWS.operational _: () => Boolean).when().returning(true)
      (stubWS.getWeather _).when("PDX").returning(Weather(55.0, 0.0, 1012.0))
      (stubWS.getWeather _).when("SFO").returning(Weather(65.0, 0.3, 1008.0))

      val tolerance = 1e-6

      lookupWeather(stubWS, "SFO").get should have (
        'temp(65.0),
        'precip(0.3),
        'pressure(1008.0)
      )

      lookupWeather(stubWS, "PDX").get should have (
        'temp(55.0),
        'precip(0.0),
        'pressure(1012.0)
      )

      (stubWS.operational _: () => Boolean).verify().twice()
      (stubWS.getWeather _).verify("PDX")
      (stubWS.getWeather _).verify("SFO")
    }
  }

  describe ("Various matchers") {
    describe("on a list of numbers") {

      val nums = (1 to 20).toList
      val threeMults = nums.filter(_ % 3 == 0)

      they("should allow a wide and varied language for matching conditions") {
        val x = 10 * 2

        x should be(20)


        threeMults should have size (6)
        threeMults should contain allOf (3, 6, 12, 15)
        threeMults should not contain (10)
        threeMults should be (Vector(3, 6, 9, 12, 15, 18))
        threeMults should be (sorted)
        all(threeMults) should be > (0)
        atLeast(3, threeMults) should be > (10)
      }
    }

    describe("on a case class example") {
      they("should allow easy field checking") {
        case class Person(first: String, last: String, age: Int)

        val p1 = Person("Harry", "Potter", 34)

        p1 should have(
          'first ("Harry"),
          'last ("Potter"),
          'age (34)
        )
      }
    }
  }

  describe ("Handling exceptions") {
    it ("should expect and intercept exceptions") {
      an [IllegalArgumentException] should be thrownBy {
        require(1 == 2, "One equals two?")
      }

      val ae = intercept [ArithmeticException] (1 / 0)
      ae.getMessage should be ("/ by zero")

    }
  }

  describe ("Floating point values") {
    they ("should be matched within a tolerance") {
      val sqrt = math.sqrt(2.0)
      sqrt should be (1.41421356 +- 1e-6)
      sqrt should (be > 1.41421355 and be < 1.41421357)
    }
  }

  import org.scalacheck.Gen

  val validChars = ('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9')
  val char = Gen.oneOf(validChars)
  val strGen = for {
    n <- Gen.choose(0, 30)  // 0 to 30 length strings
    seqChars <- Gen.listOfN(n, char)
  } yield seqChars.mkString


  describe ("Property checks and generators") {
    they ("should test .reverse.reverse on any string gives you back the original string") {
      forAll(strGen) { str =>
        println(str)
        str.reverse.reverse should be (str)
      }
    }

    they ("should ensure abs on all Ints returns a positive number") {
      forAll { (i: Int) =>
        whenever(i != Int.MinValue) {
          i.abs should be >= 0
        }
      }
    }
  }
}
