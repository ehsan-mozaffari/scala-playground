package designpattern.mediator.chatsystem

abstract class AbstractVisitor {
  var currentChatroom: AbstractChatroom = _
  var name: String

  def send(message: String): Unit ={
    println(s"$name -----> $message")
    currentChatroom.send(message, this)
  }

  def receive(message: String)

  def enter(chatroom: AbstractChatroom)={
    if(currentChatroom !=null){
      leave()
    }
    chatroom.register(this)
    currentChatroom = chatroom
  }

  def leave () = {
    if (currentChatroom != null){
      currentChatroom.unregister(this)
      currentChatroom = null
    }
  }

}
