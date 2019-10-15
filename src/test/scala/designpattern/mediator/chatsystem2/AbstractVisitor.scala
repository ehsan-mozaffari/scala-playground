package designpattern.mediator.chatsystem2

abstract class AbstractVisitor {
  val mediator: AbstractMediator = new Mediator
  val name: String

  def send(message: String): Unit ={
    println(s"$name -----> $message")
    mediator.send(message, this)
  }

  def receive(message: String): Unit = println(s"$name <--- $message")

  def enter(chatroom: AbstractChatroom)={
    println(s"enter $name to ${chatroom.name}")
    mediator.enterToChatRoom(this,chatroom)
  }


}

class Visitor(val name: String) extends AbstractVisitor {
  override def receive(message: String): Unit = println(s"$name <--- $message")
}
