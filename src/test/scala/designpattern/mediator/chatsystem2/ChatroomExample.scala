package designpattern.mediator.chatsystem2

object ChatroomExample extends App {

  val mediator = new Mediator
  val tom      = new Visitor("Tom", mediator)
  val harry    = new Visitor("Harry", mediator)
  val jean     = new Visitor("Jean", mediator)

  var chatroom = new Chatroom("AAAAAAAAAAAAAAA", mediator)
  tom.enter(chatroom)
  harry.enter(chatroom)
  jean.enter(chatroom)

  tom.send("Hello everyone")
  harry.send("Welcome to the chatroom Tom")
  jean.send("Hi Tom, nice to meet you!")

}
