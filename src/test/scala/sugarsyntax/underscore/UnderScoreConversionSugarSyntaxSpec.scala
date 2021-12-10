package sugarsyntax.underscore

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class UnderScoreConversionSugarSyntaxSpec extends AnyFlatSpec with should.Matchers {

  // ToDo Read this!
  "Underscore sugar syntax" should
    "The first conversion concerns varargs that can be constructed from a sequence with seqName: _* expression" in {
      val lettersSeq = Seq("A", "B", "C", "D")

      def concatenateLetters(multiArgs: String*): String = lettersSeq.mkString(",")

      // val a = concatenateLetters("1","2")
      // val b = concatenateLetters(lettersSeq)
      val result = concatenateLetters(lettersSeq: _*)

      result shouldEqual "A,B,C,D"
    }

  // https://stackoverflow.com/questions/2529184/difference-between-method-and-function-in-scala
  // A function is an object that includes one of the FunctionX traits, such as Function0, Function1, Function2, etc.
  // It might be including PartialFunction as well, which actually extends Function1.
  // val o1 = f(List(1, 2, 3))
  // val o2 = m(List(1, 2, 3))
  // These calls are actually different, because the first one is just a syntactic sugar. Scala expands it to:
  // val o1 = f.apply(List(1, 2, 3))
  // Another similarity between a method and a function is that the former can be easily converted into the latter:
  // val f = (l: List[Int]) => l mkString "" //Is a function
  // def m(l: List[Int]) = l mkString "" // Is a method
  // val f = m _
  // val f = new AnyRef with Function1[List[Int], AnyRef] {
  //  def apply(x$1: List[Int]) = this.m(x$1)
  // }
  // Notice that one can't convert the other way around -- from a function to a method
  // Methods, however, have one big advantage (well, two -- they can be slightly faster):
  // they can receive type parameters. For instance, while f above can necessarily specify
  // the type of List it receives (List[Int] in the example), m can parameterize it:
  // def m[T](l: List[T]): String = l mkString ""
  "Underscore sugar syntax" should
    "convert method to a function. aka Eta expansion of method into method value" in {
      def concatenateLetters(letter1: String, letter2: String): String = s"$letter1,$letter2"

      val concatenationFunction: (String, String) => String = concatenateLetters _

      val aAndBConcatenated: String = concatenationFunction(v1 = "A", v2 = "B")

      aAndBConcatenated shouldEqual "A,B"
    }

  "Underscore sugar syntax" should
    "should be used in partially applied functions" in {

      def add(number1: Int, number2: Int, number3: Int): Int = number1 + number2 + number3

      val addTo1: (Int, Int) => Int = add(_, _, 1)

      val add1To1To1Result = addTo1(1, 1)

      add1To1To1Result shouldEqual 3
    }

}
