package designpattern.mediator.chatsystem2

import scala.collection.mutable.ListBuffer

abstract class AbstractMediator {
  var currentChatroom: AbstractChatroom            = _
  val activeVisitors:  ListBuffer[AbstractVisitor]

//  def registerChatRoom(chatroom: AbstractChatroom) =
//    currentChatroom = chatroom

//  def updateActiveVisitors(newActiveVisitors: ListBuffer[AbstractVisitor]) =
//    activeVisitors = newActiveVisitors

  def send(message: String, sender: AbstractVisitor) =
    currentChatroom.send(message, sender)

  def enterToChatRoom(visitor: AbstractVisitor, chatroom: AbstractChatroom) = {

    chatroom.register(visitor)
    currentChatroom = chatroom
  }

  def leave(visitor: AbstractVisitor) =
    if (currentChatroom != null) {
      println(s"leaving ${visitor.name} from ${currentChatroom.name}")
      currentChatroom.unregister(visitor)
      currentChatroom = null
    }

  def addVisitorToChatroom(visitor: AbstractVisitor) = activeVisitors += visitor

  def removeVisitorToChatroom(visitor: AbstractVisitor) = activeVisitors -= visitor

  def sendMessegeToChatroom(message: String, sender: AbstractVisitor) =
    activeVisitors.foreach(visitor => if (visitor != sender) visitor.receive(message))
}

class Mediator extends AbstractMediator {
  val activeVisitors = new ListBuffer[AbstractVisitor]()

}
