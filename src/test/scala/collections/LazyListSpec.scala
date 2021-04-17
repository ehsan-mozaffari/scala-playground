package collections

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers


class LazyListSpec extends AnyFlatSpec with Matchers {

  "LazyList" should
    "be defined via #:: and LazyList.empty" in {

    val testLazyList = 1 #:: 2 #:: 3 #:: 4 #:: LazyList.empty


    testLazyList.toString shouldBe "LazyList(<not computed>)"
  }
}
