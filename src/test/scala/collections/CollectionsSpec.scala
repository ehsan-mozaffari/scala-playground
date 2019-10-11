package collections

import org.scalatest.{FlatSpec, Matchers}

class CollectionsSpec extends FlatSpec with Matchers {

  // ToDo Read this!
  "Collections" should
  "The first conversion concerns varargs that can be constructed from a sequence with seqName: _* expression" in {
    val lettersSeq = Seq("A", "B", "C", "D")

    def concatenateLetters(multiArgs: String*): String =
      multiArgs.mkString(",")

    val result = concatenateLetters(lettersSeq: _*)

    result shouldEqual "A,B,C,D"
  }

  "Collections" should
  "unzip a list" in {
    val lettersSeq: List[(Int, String)] = List((1, "one"), (2, "two"), (3, "three"))

    val result: (List[Int], List[String]) = lettersSeq.unzip

    result._1 shouldBe List(1, 2, 3)
    result._2 shouldBe List("one", "two", "three")
  }

  "Collections" should
  "zipWithIndex a list" in {
    val lettersSeq: List[String] = List("one", "two", "three")

    val result: List[(String, Int)] = lettersSeq.zipWithIndex

    result shouldBe List(("one", 0), ("two", 1), ("three", 2))
  }
}
