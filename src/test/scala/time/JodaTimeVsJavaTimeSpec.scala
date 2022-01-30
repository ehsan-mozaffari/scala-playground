package time

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.DateTimeZone

class JodaTimeVsJavaTimeSpec extends AnyFlatSpec with should.Matchers {

  "Joda time vs java time string comparison" should
    "match the toString of them" in {}

  /** An Instant is defined as a moment in the datetime continuum specified as a number of milliseconds from
    * 1970-01-01T00:00Z. This definition of milliseconds is consistent with that of the JDK in Date or Calendar
    */
  behavior of "Joda instant"

  it should "match the toString of them" in {

    val dateTimeFormat = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss")
    val `Dec 7, 2021 19:21:30`: DateTime = dateTimeFormat.parseDateTime("07/12/2021 19:21:30")

    `Dec 7, 2021 19:21:30`.withZone(DateTimeZone.UTC).toLocalDateTime.toString shouldBe "2021-12-07T15:51:30.000"
    `Dec 7, 2021 19:21:30`.toString shouldBe "2021-12-07T19:21:30.000+03:30"
  }

}
