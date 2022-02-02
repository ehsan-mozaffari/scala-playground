package collections

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

import java.util.NoSuchElementException
import scala.collection.mutable

class CollectionsSpec extends AnyFlatSpec with should.Matchers {

  // ToDo Read this!
  "Collections"   should
    "The first conversion concerns varargs that can be constructed from a sequence with seqName: _* expression" in {
      val lettersSeq = Seq("A", "B", "C", "D")

      def concatenateLetters(multiArgs: String*): String = multiArgs.mkString(",")

      val result = concatenateLetters(lettersSeq: _*)

      result shouldEqual "A,B,C,D"
    }

  "Immutable Set" should
    "add and see the overlaps the variables" in {

      val set1: Set[Int] = Set(1, 2, 3, 4, 5, 5, 5, 6, 7, 7, 8, 9)
      val set2: Set[Int] = Set(3, 3, 4, 5, 10)

      set1           should have size 9
      set2           should have size 4
      set1 & set2  shouldBe Set(3, 4, 5)
      set1 ++ set2 shouldBe Set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
      set1.head      should not equal 1 // Think about it
      // set1.tail should not equal set1 - set1.head is not equal always
      set2 + 6     shouldBe Set(3, 4, 5, 6, 10)
      set1(5)      shouldBe true
      set1(10)     shouldBe false
      set1(5)      shouldBe set1.contains(5)

    }

  "Immutable Map" should
    "works as expected" in {
      val mappedHotelById = Map(
        1 -> "Espinas",
        (2, "Behrood"),
        2 -> "Khalijefars",
        2 -> "Esteghlal",
        3 -> "Hilton",
        3 -> "Morvarid"
      )

      mappedHotelById.get(3)                                shouldBe Some("Morvarid")
      mappedHotelById.get(2)                                shouldBe Some("Esteghlal")
      mappedHotelById.head                                  shouldBe (1, "Espinas")
      mappedHotelById.head                                  shouldBe (1, "Espinas")
      mappedHotelById(3)                                    shouldBe "Morvarid"
      an[NoSuchElementException]                              should be thrownBy mappedHotelById(5)
      the[NoSuchElementException] thrownBy mappedHotelById(5) should have message "key not found: 5"
    }

  "array"         should
    "be mutable all the time and works as expected" in {

      val bags:                    Array[AnyVal] = Array(1, true, 1f)
      val fruits:                  Array[String] = Array("apple", "banana", "cherry", "banana")
      val fruitsBananaToRaspberry: Unit          = fruits.update(1, "raspberry")

      fruits(0) shouldBe "apple"
      fruits(1)   should not equal "banana"
      fruits(1) shouldBe "raspberry"
      fruits(0) shouldBe fruits.apply(0)
      //    fruits(0) = "raspberry" shouldBe "raspberry"
      fruits.filter(fruits.contains).headOption.getOrElse(-1)
    }

  "mutable array" should
    "be mutable as scala 2.13" in {

      val mutableArray = mutable.ArraySeq(1, 2, 3)
      mutableArray.update(0, 10)
      mutableArray(0) shouldBe 10
    }

  "List"          should
    "be created with multiple options" in {

      val hawaiiFruits:  List[String] = List("Mango", "banana")
      val iranianFruits: List[String] = "Albalo" :: "Shaftalo" :: Nil

      hawaiiFruits ::: iranianFruits           shouldBe List("Mango", "banana", "Albalo", "Shaftalo")
      List.range(10, 15)                       shouldBe List(10, 11, 12, 13, 14)
      List.range(10, 20, 5)                    shouldBe List(10, 15)
      List.concat(hawaiiFruits, iranianFruits) shouldBe hawaiiFruits ::: iranianFruits
    }

}
