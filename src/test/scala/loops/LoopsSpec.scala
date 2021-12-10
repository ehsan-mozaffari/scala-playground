package loops

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers._

import scala.collection.immutable._

class LoopsSpec extends AnyFlatSpec with should.Matchers {

  "while loops"    should
    "dont have any response" in {

      var i = 5
      var s = ""
      while (i >= 0) {
        s += s"$i,"
        i -= 1
      }
      s shouldEqual "5,4,3,2,1,0,"
    }

  "do while loops" should
    "dont have any response" in {
      var i = 5
      var s = ""
      do {
        s += s"$i,"
        i -= 1
      } while (i >= 0)

      s shouldEqual "5,4,3,2,1,0,"
    }

  "for"            should
    "loops over the collections" in {

      val fruit           = List("apples", "bananas", "cherries")
      var separatedResult = ""
      val unitResult: Unit =
        for (f <- fruit)
          separatedResult += s"$f,"

      val result: Seq[String] = for (f <- fruit) yield f

      val complexResult: Seq[String] =
        for {
          f <- fruit
          g <- fruit
        } yield s"($f, $g)"

      val complexResultWithGuard: Seq[String] =
        for {
          f  <- fruit
          g  <- fruit if g.startsWith("a") || g.startsWith("b")
          len = g.length if len > 6
        } yield s"($f, $g)"

      separatedResult shouldEqual "apples,bananas,cherries,"
      result shouldEqual fruit
      unitResult shouldEqual ()
      complexResult.mkString(
        ","
      ) shouldEqual "(apples, apples),(apples, bananas),(apples, cherries),(bananas, apples),(bananas, bananas),(bananas, cherries),(cherries, apples),(cherries, bananas),(cherries, cherries)"

      complexResultWithGuard.mkString(",") shouldEqual "(apples, bananas),(bananas, bananas),(cherries, bananas)"
    }

}
