package option

import org.scalatest.FlatSpec
import org.scalatest.Matchers

case class User(id: Int, firstName: String, lastName: String, age: Int, gender: Option[String])

class OptionSpec extends FlatSpec with Matchers {
  val SampleUser1 = User(1, "John", "Doe", 32, Some("male"))
  val SampleUser2: Option[User] = Option(User(id=2,firstName = null, lastName = "Doe", age = 30, gender = None))

  "Option" should
  "Return None if null" in {

    val testUser2 = SampleUser2
    val a: Option[String] = testUser2.map(_.firstName)
    val b: Option[String] = a.flatMap(Option(_))
    val c: Option[Option[String]] = a.map(Option(_))

    val d: Option[String] = testUser2.map(_.firstName).flatMap(Option(_))

    a shouldEqual Some(null)
    b shouldEqual None
    c shouldEqual Option(None)
    c shouldEqual Some(None)

    d shouldEqual None

  }
}
