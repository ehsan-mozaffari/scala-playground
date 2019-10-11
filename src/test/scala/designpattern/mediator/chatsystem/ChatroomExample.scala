package designpattern.mediator.chatsystem

object ChatroomExample extends App {

  val tom = new Visitor("Tom")
  val harry = new Visitor("Harry")
  val jean = new Visitor("Jean")

  var chatroom = new Chatroom
  tom.enter(chatroom)
  harry.enter(chatroom)
  jean.enter(chatroom)

  tom.send("Hello everyone")

  harry.send("Welcome to the chatroom Tom")
  jean.send("Hi Tom, nice to meet you!")
}
