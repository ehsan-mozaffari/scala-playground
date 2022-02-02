package collections

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import scala.annotation.nowarn

@nowarn // That is because the playground usage of stream in scala
@Deprecated
class StreamSpec extends AnyFlatSpec with Matchers {

  "Stream" should
    "be defined via #:: and Stream.empty deprecated and must used LazyList and not be computed until we need the values" in {

      val testStream: Stream[Int] = 1 #:: 2 #:: 3 #:: 4 #:: Stream.empty
      val testLazyList = 1 #:: 2 #:: 3 #:: 4 #:: LazyList.empty
//    val testStreamWithTo = (1 to 100000000).toStream // is so slow uncomment this to see
      val testStreamWithRange: Stream[Int] = Stream.range(1, 1000000000)

      testStream.toString          shouldBe "Stream(1, <not computed>)"
      testLazyList.toString        shouldBe "LazyList(<not computed>)"
      testStreamWithRange.toString shouldBe "Stream(1, <not computed>)"
    }

}
