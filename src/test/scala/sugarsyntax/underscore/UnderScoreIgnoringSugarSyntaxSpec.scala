package sugarsyntax.underscore

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

/**
 * The sugarsyntax.underscore can be used to ignore things as: not used variables or not used types.
 */
class UnderScoreIgnoringSugarSyntaxSpec extends AnyFlatSpec with should.Matchers {

  // Let's start by showing how to ignore the former ones. For instance,
  // if we split some text and we want to get only the first of so constructed entries, we could write the code as it
  "underscoreSugarSyntax for ignoring" should
    "should ignore not used variables" in {
    val text = "a,b"
    val Array(firstLetter, _) = text.split(",")

    firstLetter shouldEqual "a"
  }

  "underscoreSugarSyntax for ignoring" should
    "should ignore parameter in the mapping function" in {
    val numbers: Seq[Int] = 1 to 10

    val numberSeq: Seq[String] = numbers.map(_ => "Number")
    val numberStrSeq: Seq[(String, Int)] = numbers.map(_ => ("Number", 2))

    // have existed in matcherword that Matcher extend from it
    (numberSeq should have).size(10)
    (numberSeq should contain).only("Number")
    (numberStrSeq should have).size(10)
  }

  "underscoreSugarSyntax for anonymized parameters in the functions (aka placeholders)" should
    "anonymize parameter in a short function" in {
    val lettersUpperCase = Seq("A", "B", "C")

    val lettersLowerCase = lettersUpperCase.map(_.toLowerCase())

    lettersLowerCase should have size 3
    lettersLowerCase should contain allOf("a", "b", "c")
  }
  // Bad practice
  "underscoreSugarSyntax for anonymized parameters in the functions (aka placeholders)" should
    "should anonymize parameter in a lot of chained functions" in {
    val lettersWithNumbers: Seq[(String, Int, Boolean)] =
      Seq(("A", 1, true), ("B", 2, true), ("C", 3, true), ("D", 4, false))

    val lettersEvenAndTrue: Seq[String] = lettersWithNumbers
      .filter(_._2 % 2 == 0)
      .filter(_._3)
      .map(_._1)

    val a: Seq[(String, Int, Boolean)] = lettersWithNumbers.filter(_._2 % 2 == 0)

    lettersEvenAndTrue should have size 1
    lettersEvenAndTrue should contain only "B"
  }

  "underscoreSugarSyntax" should
    "ignore type in the pattern matching" in {
    val lettersSet: Set[String] = Set("A", "B", "C")

    val collectionType: String = lettersSet match {
      case _: Set[_] => "set"
      case _: List[_] => "list"
      case _ => "unknown"
    }

    collectionType shouldEqual "set"
  }

}
