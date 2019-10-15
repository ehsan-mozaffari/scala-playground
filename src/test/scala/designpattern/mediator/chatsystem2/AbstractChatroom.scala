package designpattern.mediator.chatsystem2

abstract class AbstractChatroom {
  val mediator: AbstractMediator = new Mediator
  val name: String
  def send(message:       String, sender: AbstractVisitor)
  def unregister(visitor: AbstractVisitor)
  def register(visitor:   AbstractVisitor)

}

class Chatroom(val name: String) extends AbstractChatroom {

  override def send(message: String, sender: AbstractVisitor): Unit =
    mediator.sendMessegeToChatroom(message, sender)

  override def unregister(visitor: AbstractVisitor): Unit =
    mediator.removeVisitorToChatroom(visitor: AbstractVisitor)

  override def register(visitor: AbstractVisitor): Unit =
    mediator.addVisitorToChatroom(visitor: AbstractVisitor)
}
