package sugarsyntax.underscore

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class UnderScoreMiscellaneousSugarSyntaxSpec extends AnyFlatSpec with should.Matchers {

  // he underscore can be used as universal substitution (we can perceive is also as ignore though),
  // for instance, in the wildcard imports:
  // import org.scalatest._

  "Underscore sugar syntax for universality" should
    "be used in pattern matching as here for \"everything else\":" in {

      val text: Any = "a, b, c, d"

      val matchedType =
        text match {
          case nrInt:  Int  => "int"
          case nrLong: Long => "long"
          case _ => "everything else"
        }

      matchedType shouldEqual "everything else"
    }

  // TODO Ask from Hamed or Arya
  "Underscore sugar syntax for universality" should
    "bind the whole matching sequence to a variable" in {

      val lettersSeq: Seq[String] = Seq("A", "B", "C", "D@")

      val lowerCasedLetters: Seq[String] =
        lettersSeq match {
          case Seq(matchedStrings @ _*) => matchedStrings.map(symbol => symbol.toLowerCase())
          case _                        => Seq.empty
        }

      lowerCasedLetters should have size 4
      lowerCasedLetters should contain allOf ("a", "b", "c", "d@")
    }

  // setters overriding - Scala classes setters are automatically created with public variables.
  // However sometimes we may need to override it in order to add some extra logic.
  // We can do it by suffixing the method named the same as the attribute with _:
  "Underscore sugar syntax for setter overriding" should
    "should override the setter" in {

      class Letter {
        var isInitialized      = false
        private var localValue = ""

        def value = localValue

        def value_=(newValue: String): Unit = {
          isInitialized = true
          localValue    = newValue
        }
      }

      val a = new Letter()
      a.value = "a"

      a.isInitialized shouldBe true
      a.value shouldEqual "a"
    }

  // the underscore can be used to define higher-kinded and existential types.
  // But they will be explained in one of later posts. Now it's enough to know that the higher-kinded
  // type is the way to define a type abstracting over some type that, in its own turn, abstracts over
  // other type. Simply speaking it's Scala's ability to generalize across type constructors:
  "Underscore sugar syntax for types"     should
    "should construct higher-kinded types" in {

      // AnyObjectContainer parameterized by a first-order type, thus it becomes a higher-kinded type
      trait AnyObjectContainer[A[_]] {
        def checkIfEmpty[T](collection: A[T]): Boolean
      }
      object SeqObjectContainer extends AnyObjectContainer[Seq] {
        override def checkIfEmpty[T](collection: Seq[T]): Boolean = !collection.nonEmpty
      }

      val seqIsEmpty: Boolean = SeqObjectContainer.checkIfEmpty[String](Seq("A"))

      seqIsEmpty shouldBe false
    }

  "Underscore sugar syntax for self type" should
    "should reassign this in self type" in {

      trait Programmer {
        def languages: String
      }
      trait TeamLead   {
        _: Programmer => // if you change _ to self or this the result is the same
        def showPreferences: String = s"TeamLead preferences are $languages"
      }
      // because of self typed TeamLead, ScalaTeamLead must be mixed with Programmer
      class ScalaTeamLead extends TeamLead with Programmer {
        override def languages: String = "Scala"
      }
      val scalaTeamLead = new ScalaTeamLead()

      val scalaTeamLeadPreferences = scalaTeamLead.showPreferences

      scalaTeamLeadPreferences shouldEqual "TeamLead preferences are Scala"
    }

  var isTrue: Boolean = _

  "Underscore sugar syntax for default"            should
    "should set the type to its default value" in {

      isTrue shouldBe false
    }

  "Underscore sugar syntax for tuples and getters" should
    "should be retrieved with _2" in {

      val letters      = ("A", "B", "C")
      val secondLetter = letters._2
      secondLetter shouldEqual "B"
    }

  // If you wanted to use ! @ and all other alphanumerics you have to write _ before them
  "Underscore sugar syntax for identifiers naming" should
    "should allow to use underscore to separate alphanumerics from symbols on identifiers" in {

      val hello_! = "Hello !"

      hello_! shouldEqual "Hello !"
    }

}
