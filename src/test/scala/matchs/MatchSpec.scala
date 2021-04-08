package matchs

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class MatchSpec extends AnyFlatSpec with should.Matchers {

  "scala match case" should
    "works with | (or) operation" in {

    val daysOfWeek = Seq("Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday")
    val result = "Monday" match {
      case "Monday"| "Tuesday"| "Wednesday"| "Thursday"| "Friday" => "Weekday!"
      case "Saturday"| "Sunday" => "Weekend :)"
      case _ => "Invalid Day"
    }

    result shouldBe "Weekday!"

  }
}
