package algorithm.trees

import org.scalatest.GivenWhenThen
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class BTreeSpec extends AnyFlatSpec with should.Matchers with GivenWhenThen {

  behavior of "-" * 50 + "BTree" + "-" * 50

  """
    |        _____1_____
    |       /           \
    |     __2__       __6__
    |    /     \     /     \
    |    3     4     7     8
    |           \
    |            5
    |""".stripMargin should
    """calculates like the following:
      | collectLeaves([1], []) =
      | collectLeaves([2,6], []) =
      | collectLeaves([3,4,6], []) =
      | collectLeaves([4,6], [3]) =
      | collectLeaves([5,6], [3]) =
      | collectLeaves([6], [5,3]) =
      | collectLeaves([7,8], [5,3]) =
      | collectLeaves([8], [7,5,3]) =
      | collectLeaves([], [8,7,5,3]) =
      | [8,7,5,3]
      |""".stripMargin in {

    //@formatter:off
    Given("A binary tree with 4 leaves")

    When("the three has 4 leaves")
    val tree = BNode(1,
      BNode(2,
        BNode(3, BEnd, BEnd), // leaf
        BNode(4,
          BEnd,
          BNode(5, BEnd, BEnd), // leaf
        )
      ),
      BNode(6,
        BNode(7, BEnd, BEnd), // leaf
        BNode(8, BEnd, BEnd), // leaf
      )
    )


    Then("the tree collected leaves must be a list of 4")
    val treeLeavesResult = List(
      BNode(8,BEnd,BEnd),
      BNode(7,BEnd,BEnd),
      BNode(5,BEnd,BEnd),
      BNode(3,BEnd,BEnd),
    )
    //@formatter:on

    And("collected leaves must be equal to 4")
    tree.collectLeaves shouldBe treeLeavesResult
    And("collected leaves are 4")
    tree.leafCount shouldBe 4
    And("values of leaves must be equal")
    tree.collectLeaves.map(_.value) shouldBe List(8, 7, 5, 3)

    info("All runs successfully")
  }

  "Defined a large single left child tree" should
    "calc the size with O(N)" in {
    Given("100000 node tree")
    val degenerate = (1 to 100000).foldLeft[BTree[Int]](BEnd)((tree, tValue) => BNode(tValue, tree, BEnd))
    Then("We should get it very quickly")
    degenerate.size shouldBe 100000
  }

}
