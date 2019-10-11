package designpattern.mediator.chatsystem

import scala.collection.mutable.ListBuffer

abstract class AbstractChatroom {

  var activeVisitors: ListBuffer[AbstractVisitor] = ListBuffer[AbstractVisitor]()
  def send(message:       String, sender: AbstractVisitor)
  def unregister(visitor: AbstractVisitor)
  def register(visitor:   AbstractVisitor)

}
