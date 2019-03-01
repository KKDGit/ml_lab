
/* Copyright (C) 2010-2018 Escalate Software, LLC. All rights reserved. */

package koans
import org.scalatest.Matchers
import java.net.InetAddress
import annotation.tailrec
import org.scalatest.{SeveredStackTraces, FunSuite}


class Module07 extends FunSuite with Matchers with SeveredStackTraces {

  object InetAddrNameResolver {
    // used to be actual internet lookup, but was too mutable, so fake slow now.
    def addressForName(name: String): Option[String] = {
      Thread.sleep(2000)
      MockAddrNameResolver.addressForName(name)
    }
  }

  // a mock version - notice the similarity of the API :-)
  object MockAddrNameResolver {
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

    val nameResolver = InetAddrNameResolver

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

    // The following test works just fine, but it's slow. In real life it would be connected
    // to the internet and this means that if there is ever some kind of network
    // problem, your tests will fail, not to mention the extra speed a mock can give you.

    // Make the mock share a trait called NameResolver with the real impl, then make the necessary changes to
    // SiteNewAgeChecker that will allow the Mock to be used instead of the InetAddress backed one.
    // This is a very typical use of modular decoupling.
    
    val hostList = List("localhost", "www.cnn.com", "www.slashdot.org", "SomeTotallyMadeUpHostNameXXYYZZ.com")

    val checker = new SiteNewAgeChecker

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

    // Create a DBConfig trait with a single abstract db val of type DBAccess

    // Create a NameResolverConfig trait with a single abstract nameResolver val
    // of type NameResolver (or whatever common super-trait you used for the name
    // resolver above)

    // Create a SystemConfig trait that mixes DBConfig and NameResolverConfig
    // together into one handy package


    // alter this SiteNumerologer to take the implicit config NameResolverConfig
    // and use that to get the name resolver instead of just using the slow one
    class SiteNumerologer {
      import NumerCalc._
      val resolver = InetAddrNameResolver

      def numerForSite(site: String): Int = {
        val addr = resolver.addressForName(site)
        addr.map(numerologyOfString).getOrElse(0)
      }
    }


    // Alter the SiteLookerUpper to take implicit DBConfig and use that to look
    // up the db instead of using the slow one
    class SiteLookerUpper {
      val db = SlowDB
      def numerForIndex(i: Int): String = db.lookupSite(i)
    }


    // now you will need to introduce the SystemConfig implicit here, since
    // the new SiteLookerUpper and new SiteNumerologer need to have those
    // configs available, and SystemConfig covers them both. Add the implicit param
    // and you should be good to go
    class DBNumerologer {
      def numerForIndex(i: Int): Int = {
        val lookerUpper = new SiteLookerUpper
        val numerologer = new SiteNumerologer
        numerologer.numerForSite(lookerUpper.numerForIndex(i))
      }
    }


    // Finally you actually need an implicit concrete instance of the SystemConfig
    // with the FakeDB and MockAddrNameResolver in it - the compiler will tell
    // you all of this if you forget. Try switching in the slow impls to this
    // config to see how easy it is to switch back and forth
    val dbNumer = new DBNumerologer    // implicits all the way down, like turtles

    dbNumer.numerForIndex(1) should be (2)
    dbNumer.numerForIndex(2) should be (8)
  }

  // Extra credit, if you have time, try the same system configuration using Cake
  // instead of Parfait
}
