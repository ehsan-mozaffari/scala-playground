package designpattern.mediator.chatsystem

class Chatroom extends AbstractChatroom {

  override def send(message: String, sender: AbstractVisitor): Unit =
    activeVisitors.foreach(visitor => if (visitor != sender) visitor.receive(message))

  override def unregister(visitor: AbstractVisitor): Unit = activeVisitors -= visitor

  override def register(visitor: AbstractVisitor): Unit = activeVisitors += visitor
}
