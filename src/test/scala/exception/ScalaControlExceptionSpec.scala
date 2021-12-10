package exception

import java.io.Serializable

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import scala.language.postfixOps

import scala.util.Failure
import scala.util.control.Exception._

class ScalaControlExceptionSpec extends AnyFlatSpec with should.Matchers {

  val SourceOfStudy = "https://piyushmishra889.wordpress.com/2014/12/15/scalas-advance-exception-handling-techniques/"

  val testInt                       = 10
  val testConvertibleStringToInt    = testInt.toString
  val testNonConvertibleStringToInt = "non convertible int"

  "CatchAll"                should
    "Fill option None if throws exception and Some if not" in {

      allCatch.opt(testConvertibleStringToInt.toInt)    shouldBe Some(testInt)
      allCatch.opt(testNonConvertibleStringToInt.toInt) shouldBe None
    }

  "CatchAll"                should
    "Try Should return Failure" in {
      allCatch.toTry(testNonConvertibleStringToInt.toInt) shouldBe an[Failure[NumberFormatException]]
      allCatch.toTry(testConvertibleStringToInt.toInt)    shouldBe testInt
    }

  "CatchAll"                should
    "return Either Left if fail and actual value if it's OK" in {
      allCatch.toEither(testNonConvertibleStringToInt.toInt) shouldBe an[Left[NumberFormatException, Int]]
      allCatch.toEither(testConvertibleStringToInt.toInt)    shouldBe testInt
    }

  "catching"                should
    "return Option Some and either Right if success and Option None or Either Left if fail " +
    "and if its thrown unexpected exception not defined in seq it throws exception" in {

      val exceptions = Seq(classOf[ArithmeticException], classOf[NullPointerException])

      catching(exceptions: _*).opt(1 / 0) shouldBe None

      catching(exceptions: _*).either(1 / 0) shouldBe an[Left[NumberFormatException, Int]]

      catching(exceptions: _*).either(testConvertibleStringToInt.toInt) shouldBe Right(testInt)

      an[NumberFormatException] shouldBe thrownBy(catching(exceptions: _*).opt(testNonConvertibleStringToInt.toInt))
    }

  "catchSpecificExceptions" should
    "Right or Left exception" in {

      def catchSpecificExceptions[A](
        exceptions: Class[_]*
      )(body:       => A): Product with Serializable with scala.util.Either[Throwable, A] = catching(exceptions: _*)
        .either(body)
        .fold(
          { t: Throwable =>
            t match {
              case ex if exceptions contains ex.getClass =>
                println("Gotta Exception [" + ex + "] exeption contains ex.getClass") // log exception
                Left(ex)
              case ex: Exception =>
                println("Gotta Exception [" + ex + "]") // log exception
                Left(ex)
            }
          },
          data => Right(data)
        )

      val result =
        catchSpecificExceptions(classOf[ArithmeticException]) {
          val list = List(1, 2, 3)
          (list.map(_ + 1) sum) / 2
        }

      val noResultDividedByZero =
        catchSpecificExceptions(classOf[ArithmeticException]) {
          val list = List(1, 2, 3)
          (list.map(_ + 1) sum) / 0
        }

      result                shouldBe Right(4)
      noResultDividedByZero shouldBe an[
        Left[NumberFormatException, Int] // Todo I donno why
      ]

    }

}
