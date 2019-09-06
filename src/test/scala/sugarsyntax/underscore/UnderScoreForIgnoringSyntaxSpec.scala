package sugarsyntax.underscore

import org.scalatest.{FlatSpec, Matchers}

/**
  * The sugarsyntax.underscore can be used to ignore things as: not used variables or not used types.
  */
class UnderScoreForIgnoringSyntaxSpec extends FlatSpec with Matchers {

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
    val numbers = 1 to 10

    val numberSeq = numbers.map(_ => "Number")

    numberSeq should have size 10
    numberSeq should contain only "Number"
  }


}
