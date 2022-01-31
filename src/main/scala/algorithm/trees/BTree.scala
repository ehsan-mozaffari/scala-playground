package algorithm.trees

import scala.annotation.tailrec

/** BTree means Binary Tree. Binary three is a data structure that compose nodes. Every Binary tree has some value and a
  * reference to a left child and right child. Every node has at most two children and each node contains a value. It is
  * used as organizing data for sorting, ...
  */
sealed abstract class BTree[+T] {
  def value:   T
  def left:    BTree[T]
  def right:   BTree[T]
  def isEmpty: Boolean

  /** A leaf in a tree is a node in which both nodes (left and right) of BTree are empty. So they are the BEnd object
    */
  def isLeaf:        Boolean
  def collectLeaves: List[BTree[T]]
  def leafCount:     Int
}

case object BEnd extends BTree[Nothing] {
  def noSuchElementException = throw new NoSuchElementException

  override def value:   Nothing        = noSuchElementException
  override def left:    BTree[Nothing] = noSuchElementException
  override def right:   BTree[Nothing] = noSuchElementException
  override def isEmpty: Boolean        = true

  /** leaf node needs to automatically contain a value. So it needs to not be empty.
    */
  override def isLeaf:        Boolean              = false
  override def collectLeaves: List[BTree[Nothing]] = List() // Because there is nothing to collect
  override def leafCount:     Int                  = 0      // There are no node in this tree
}

case class BNode[+T](override val value: T, override val left: BTree[T], override val right: BTree[T])
    extends BTree[T] {
  override def isEmpty: Boolean = false

  /** Leaf is a node that has a value and both left and right node must be empty.
    * @return
    */
  override def isLeaf: Boolean = left.isEmpty && right.isEmpty

  override def collectLeaves: List[BTree[T]] = {

    @tailrec
    def collectLeavesTailrec(todo: List[BTree[T]], leaves: List[BTree[T]]): List[BTree[T]] =
      if (todo.isEmpty)
        leaves
      else if (todo.head.isEmpty)
        collectLeavesTailrec(todo.tail, leaves)
      else if (todo.head.isLeaf)
        collectLeavesTailrec(todo.tail, todo.head :: leaves)
      else {
        val node = todo.head
        collectLeavesTailrec(node.left :: node.right :: todo.tail, leaves)
      }
    collectLeavesTailrec(List(this), List())
  }

  override def leafCount: Int = collectLeaves.length

}
