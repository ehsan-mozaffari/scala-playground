package algorithm.tailrecursive

import algorithm.tailrecursive.FibonacciTailRecursive._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import org.scalatest.tagobjects._

class FibonacciTailRecursiveSpec extends AnyFlatSpec with should.Matchers {

  behavior of "FibonacciTailRecursive"

  "FibonacciTailRecursive#fibonacciRecursive"     should
    "calculate data recursively not stack safely" taggedAs CPU in {
      fibonacciRecursive(0)  shouldBe 0
      fibonacciRecursive(1)  shouldBe 1
      fibonacciRecursive(2)  shouldBe 1
      fibonacciRecursive(3)  shouldBe 2
      fibonacciRecursive(4)  shouldBe 3
      fibonacciRecursive(6)  shouldBe 8
      fibonacciRecursive(10) shouldBe 55
      fibonacciRecursive(12) shouldBe 144
    }

  "FibonacciTailRecursive#fibonacciTailRecursive" should
    "calc data with one stack frame" in {

      fibonacciTailRecursive(0)   shouldBe 0
      fibonacciTailRecursive(1)   shouldBe 1
      fibonacciTailRecursive(2)   shouldBe 1
      fibonacciTailRecursive(3)   shouldBe 2
      fibonacciTailRecursive(4)   shouldBe 3
      fibonacciTailRecursive(6)   shouldBe 8
      fibonacciTailRecursive(10)  shouldBe 55
      fibonacciTailRecursive(12)  shouldBe 144
      fibonacciTailRecursive(-12) shouldBe -144
    }

  "FibonacciTailRecursive#fibonacciLazy"          should
    "calc fib lazily" in {

      fibonacciLazy(0)  shouldBe 0
      fibonacciLazy(1)  shouldBe 1
      fibonacciLazy(2)  shouldBe 1
      fibonacciLazy(3)  shouldBe 2
      fibonacciLazy(4)  shouldBe 3
      fibonacciLazy(6)  shouldBe 8
      fibonacciLazy(10) shouldBe 55
      fibonacciLazy(12) shouldBe 144
    }

}
