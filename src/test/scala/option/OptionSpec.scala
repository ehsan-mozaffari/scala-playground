package option

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

case class User(id: Int, firstName: String, lastName: String, age: Int, gender: Option[String])

class OptionSpec extends AnyFlatSpec with should.Matchers {
  val SampleUser1 = User(1, "John", "Doe", 32, Some("male"))

  val SampleUser2: Option[User] = Option(User(id = 2, firstName = null, lastName = "Doe", age = 30, gender = None))

  "Option" should
    "Return None if null" in {

      val testUser2 = SampleUser2
      val a: Option[String]         = testUser2.map(_.firstName)
      val b: Option[String]         = a.flatMap(Option(_))
      val c: Option[Option[String]] = a.map(Option(_))

      val d: Option[String] = testUser2.map(_.firstName).flatMap(Option(_))

      a shouldEqual Some(null)
      b shouldEqual None
      c shouldEqual Option(None)
      c shouldEqual Some(None)

      d shouldEqual None

    }

  "Option# Applying a method on Option" should "Return null if it is None and Some if is not None" in {

    val testEmptyStringOption:    Option[String] = Option.empty
    val testNonEmptyStringOption: Option[String] = Some("NonEmptyOption")

    def testMethod(x: String): String = s"$x successful"

    def testMethodRetOption(x: String): Option[String] = Some(s"$x successful")

    testEmptyStringOption shouldBe None

    testEmptyStringOption.map(testMethod)              shouldBe None
    testEmptyStringOption.flatMap(testMethodRetOption) shouldBe None

    val badEmptyOptionCheck: Option[String] =
      testEmptyStringOption match {
        case Some(x) => Some(testMethod(x))
        case None    => None
      }
    badEmptyOptionCheck shouldBe None
    testEmptyStringOption.map(testMethod)              shouldBe badEmptyOptionCheck
    testEmptyStringOption.flatMap(testMethodRetOption) shouldBe badEmptyOptionCheck

    testNonEmptyStringOption                              shouldBe Some("NonEmptyOption")
    testNonEmptyStringOption.map(testMethod)              shouldBe Some("NonEmptyOption successful")
    testNonEmptyStringOption.flatMap(testMethodRetOption) shouldBe Some("NonEmptyOption successful")

    val badNonEmptyOptionCheck =
      testNonEmptyStringOption match {
        case Some(x) => Some(testMethod(x))
        case None    => None
      }
    badNonEmptyOptionCheck shouldBe Some("NonEmptyOption successful")
    testNonEmptyStringOption.map(testMethod)              shouldBe badNonEmptyOptionCheck
    testNonEmptyStringOption.flatMap(testMethodRetOption) shouldBe badNonEmptyOptionCheck
  }

}
