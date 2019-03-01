import scala.util.control.NonFatal

class NamedTest(val name: String, val test: () => Unit)

abstract class DemoSuite {
  private[this] var _tests = List.empty[NamedTest]

  protected def test(testName: String)(test: => Unit): Unit =
    _tests = new NamedTest(testName, () => test) :: _tests

  protected lazy val tests: List[NamedTest] = _tests.reverse
  def run(): Unit = {
    for (namedTest <- tests) {
      print(s"Running ${namedTest.name}: ")
      try {
        namedTest.test()
        println("Passed")
      }
      catch {
        case NonFatal(ex) =>
          println(s"Failed ${ex.getMessage}")
      }
    }
  }
}

class MyTests extends DemoSuite {
  test("1 + 1 should be 2") {
    assert(1 + 1 == 2)
  }

  test("1 + 1 should be 3") {
    assert(1 + 1 == 3)
  }

  test("1 / 0 should be 0") {
    assert(1 / 0 == 0)
  }
}

(new MyTests).run()