package example

import org.scalatest.{FlatSpec, Matchers}

class HelloTestSpec extends FlatSpec with Matchers{

  "test" should "works" in {
    assert(true)
  }

  it should "ShouldBe is in Matchers package" in {
    1 shouldBe 1
  }
}
