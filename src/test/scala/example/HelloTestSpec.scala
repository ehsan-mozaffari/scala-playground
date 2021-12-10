package example

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class HelloTestSpec extends AnyFlatSpec with should.Matchers {

  "test" should "works" in {
    assert(true)
  }

  it     should "ShouldBe is in Matchers package" in {
    1 shouldBe 1
  }

}
