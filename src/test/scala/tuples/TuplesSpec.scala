package tuples

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class TuplesSpec extends AnyFlatSpec with should.Matchers {

  "Tuple" should
    "be defined properly at max 22 values" in {
      val threeElementsTuple:       (String, Int, Boolean)   = ("Scala", 20, true)
      //    val twoElementsTupleWithoutSugarSyntaxType: Tuple2[String, String]= ("Sugar syntax", "of two Element Tuple")
      val twoElementsTupleViaArrow: (String, String)         = "Sugar syntax" -> "of two Element Tuple"
      val twoElementsTuple:         (String, String)         = ("Sugar syntax", "of two Element Tuple")
      val nestedTuple:              ((String, Int), Boolean) = "Scala"        -> 20 -> true

      val element1: String  = threeElementsTuple._1
      val element2: Int     = threeElementsTuple._2
      val element3: Boolean = threeElementsTuple._3
      val (elem1, elem2, elem3) = threeElementsTuple

      twoElementsTuple shouldBe twoElementsTupleViaArrow

      element1 shouldBe elem1
      element2 shouldBe elem2
      element3 shouldBe elem3

    }

}
