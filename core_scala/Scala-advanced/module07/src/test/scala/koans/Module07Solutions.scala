
/* Copyright (C) 2010-2018 Escalate Software, LLC. All rights reserved. */

package koans
import org.scalatest.Matchers
import java.net.InetAddress
import annotation.tailrec
import org.scalatest.{SeveredStackTraces, FunSuite}


class Module07Solutions extends FunSuite with Matchers with SeveredStackTraces {

  trait NameResolver { def addressForName(name: String): Option[String] }

  object InetAddrNameResolver extends NameResolver {
    // used to be actual internet lookup, but was too mutable, so fake slow now.
    def addressForName(name: String): Option[String] = {
      Thread.sleep(2000)
      MockAddrNameResolver.addressForName(name)
    }
  }

  object MockAddrNameResolver extends NameResolver {
    def addressForName(name: String): Option[String] = name match {
      case "localhost" => Some("127.0.0.1")
      case "www.cnn.com" => Some("157.166.248.10")
      case "www.slashdot.org" => Some("216.34.181.48")
      case _ => None
    }
  }

  object NumerCalc {
    @tailrec
    final def numerologyOfString(str: String): Int = {
      str.map(_.asDigit).sum match {
        case x if (x <= 9) => x
        case x => numerologyOfString(x.toString)
      }
    }
  }

  class SiteNewAgeChecker {
    import NumerCalc._

    val nameResolver: NameResolver = InetAddrNameResolver

    def numerologyValue(name: String): Int = {
      nameResolver.addressForName(name) match {
        case None => 0  // an unhappy numerology site
        case Some(addr) =>
          numerologyOfString(addr.filterNot(_ == '.'))
      }
    }
  }

  test ("Real InetAddrNameResolver") {
    val localhost = InetAddrNameResolver.addressForName("localhost")
    localhost.get should be ("127.0.0.1")

    val nonExistentHost = InetAddrNameResolver.addressForName("SomeTotallyMadeUpHostNameXXYYZZ.com")
    nonExistentHost should be (None)
  }

  test ("Numerology of hosts") {
    // What we really want to test here is our new age numerology site calculator, not the site resolver
    // itself since that is checked above.

    // The following test works just fine, until you disconnect from the internet. Try it (disable your
    // internet connection and run it again). This means that if there is ever some kind of network
    // problem, your tests will fail, not to mention the extra speed a mock can give you.

    // Supply a mock testing object that will allow the test to pass, then make the necessary changes to
    // SiteNewAgeChecker that will allow the Mock to be used instead of the InetAddress backed one.
    // This is a very typical use of modular decoupling.
    
    val hostList = List("localhost", "www.cnn.com", "www.slashdot.org", "SomeTotallyMadeUpHostNameXXYYZZ.com")

    val checker = new SiteNewAgeChecker { override val nameResolver = MockAddrNameResolver }

    hostList.map(checker.numerologyValue(_)) should be (List(2, 5, 2, 0))
  }

  trait DBAccess {
    val sites = IndexedSeq("localhost", "www.cnn.com", "www.slashdot.org")
    def lookupSite(i: Int): String
  }

  object SlowDB extends DBAccess {
    def lookupSite(i: Int) = {
      Thread.sleep(2000)
      sites(i)   // really complex DB operation
    }
  }

  object FakeDB extends DBAccess {
    def lookupSite(i: Int) = {
      sites(i)   // just like the above, but fast
    }
  }

  test ("Inject using Parfait") {
    trait DBConfig {
      val db: DBAccess
    }

    trait NameResolverConfig {
      val nameResolver: NameResolver
    }

    trait SystemConfig extends DBConfig with NameResolverConfig

    class SiteNumerologer(implicit val config: NameResolverConfig) {
      import NumerCalc._
      val resolver = config.nameResolver

      def numerForSite(site: String): Int = {
        val addr = resolver.addressForName(site)
        addr.map(numerologyOfString).getOrElse(0)
      }
    }

    class SiteLookerUpper(implicit val config: DBConfig) {
      val db = config.db
      def numerForIndex(i: Int): String = db.lookupSite(i)
    }

    class DBNumerologer(implicit val config: SystemConfig) {
      def numerForIndex(i: Int): Int = {
        val lookerUpper = new SiteLookerUpper
        val numerologer = new SiteNumerologer
        numerologer.numerForSite(lookerUpper.numerForIndex(i))
      }
    }

    implicit object QuickTestConfig extends SystemConfig {
      val db: DBAccess = FakeDB
      val nameResolver: NameResolver = MockAddrNameResolver
    }

    val dbNumer = new DBNumerologer    // implicit's all the way down, like turtles

    dbNumer.numerForIndex(1) should be (2)
    dbNumer.numerForIndex(2) should be (8)
  }

}
