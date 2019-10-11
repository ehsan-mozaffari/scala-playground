package designpattern.mediator.chatsystem

class Visitor(var name: String) extends AbstractVisitor {
  override def receive(message: String): Unit = println(s"$name <--- $message")
}
