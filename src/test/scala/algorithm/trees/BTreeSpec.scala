package algorithm.trees

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class BTreeSpec extends AnyFlatSpec with should.Matchers {

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
      |
      |""".stripMargin in {

    //@formatter:off
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

    val treeLeavesResult = List(
      BNode(8,BEnd,BEnd),
      BNode(7,BEnd,BEnd),
      BNode(5,BEnd,BEnd),
      BNode(3,BEnd,BEnd),
    )
    //@formatter:on

      tree.collectLeaves shouldBe treeLeavesResult
    }

}
