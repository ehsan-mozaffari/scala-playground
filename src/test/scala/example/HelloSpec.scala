package example

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class HelloSpec extends AnyFlatSpec with should.Matchers {

  "The Hello object" should "say hello" in {
    Hello.greeting shouldEqual "hello"
  }

}
