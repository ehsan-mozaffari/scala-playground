package collections

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class CollectionsSpec extends AnyFlatSpec with should.Matchers {

  // ToDo Read this!
  "Collections" should
    "The first conversion concerns varargs that can be constructed from a sequence with seqName: _* expression" in {
    val lettersSeq = Seq("A", "B", "C", "D")

    def concatenateLetters(multiArgs: String*): String =
      multiArgs.mkString(",")

    val result = concatenateLetters(lettersSeq: _*)

    result shouldEqual "A,B,C,D"
  }
}
