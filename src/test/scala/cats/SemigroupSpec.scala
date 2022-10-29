package cats

import org.scalatest.concurrent.ScalaFutures
import org.scalatest.flatspec.AsyncFlatSpec
import org.scalatest.matchers.should
//import scala.concurrent.ExecutionContext.Implicits.global

class SemigroupSpec extends AsyncFlatSpec with ScalaFutures with should.Matchers {

  "Semigroups" should
    "Combine elements of the same (main functionality) and it has |+| syntax in the implicit class" in {
      import cats.instances.int._

      Semigroup[Int].combine(10, 5)                shouldBe 15
      Semigroup[Int].combine(10, 5)                shouldBe 15
      Semigroup[String].combine("Hello", " Cats!") shouldBe "Hello Cats!"

      import cats.syntax.semigroup._
      10 |+| 15            shouldBe Semigroup[Int].combine(10, 15)
      "Hello" |+| " Cats!" shouldBe Semigroup[String].combine("Hello", " Cats!")
    }

  "Semigroup"  should
    "work on Options" in {
      import cats.instances.option._
      import cats.syntax.semigroup._

      Option(5) |+| Option(10) shouldBe Option(15)
    }
//TODO
//  "Semigroup"  should
//    "combine futures in parallel" in {
//      import cats.instances.future._
//      import cats.syntax.semigroup._
//
//      val combineFutures: Future[Long] =
//        Future { val start = System.currentTimeMillis(); println("11111");Thread.sleep(1); System.currentTimeMillis() - start } |+|
//          Future { val start = System.currentTimeMillis(); println("22222"); Thread.sleep(2); System.currentTimeMillis() - start } |+|
//          Future { val start = System.currentTimeMillis(); println("33333"); Thread.sleep(3); System.currentTimeMillis() - start }
//
//      whenReady(combineFutures)(_ should be <= 30L)
//    }

}
