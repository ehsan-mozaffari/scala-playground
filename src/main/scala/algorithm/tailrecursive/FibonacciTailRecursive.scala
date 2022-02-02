package algorithm.tailrecursive

import scala.annotation.tailrec

object FibonacciTailRecursive {

  def fibonacciRecursive(index: Int): Int =
    if (index <= 0)
      0
    else if (index == 1)
      1
    else
      fibonacciRecursive(index - 1) + fibonacciRecursive(index - 2)


  /**
   * @formatter:off
   * fibTailRec(10, 0, 1)
   * fibTailRec(9, 1, 1)
   * fibTailRec(8, 1, 2)
   * fibTailRec(7, 2, 3)
   * fibTailRec(6, 3, 5)
   * fibTailRec(5, 5, 8)
   * fibTailRec(4, 8, 13)
   * fibTailRec(3, 13, 21)
   * fibTailRec(2, 21, 34)
   * fibTailRec(1, 34, 55) =
   * 55
   * @formatter:on
   */
  def fibonacciTailRecursive(index: Int): BigInt = {
    @tailrec
    def fibTailRec(idx: Int, fibPrePre: BigInt, fibPre: BigInt): BigInt =
      idx match {
        case 0 => fibPrePre
        case 1 => fibPre
        case _ => fibTailRec(idx - 1, fibPre, fibPrePre + fibPre)
      }

    if (index < 0) -fibTailRec(-index, 0, 1)
    else fibTailRec(index, 0, 1)
  }

  def fibonacciLazy(index: Int): BigInt = {
    def fibLazy(fibPrePre: BigInt, fibPre: BigInt): LazyList[BigInt] = fibPrePre #:: fibLazy(fibPre, fibPrePre + fibPre)

    fibLazy(0, 1).take(index + 1).toList.lastOption.getOrElse(BigInt(0))
  }

}
