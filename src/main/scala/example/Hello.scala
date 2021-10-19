package example

trait Greeting {
  lazy val greeting: String = "hello"
}

object Hello extends Greeting with App {

  println(greeting)

  println("See the test section of it")
  println("See: https://www.waitingforcode.com/scala-syntax/syntactic-sugar-scala/read")
  println("See: https://www.waitingforcode.com/scala-functional/partially-applied-functions-scala/read")
}
