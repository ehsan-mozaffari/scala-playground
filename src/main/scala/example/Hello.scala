package example

trait Greeting {
  lazy val greeting: String = "hello"
}

object Hello extends Greeting with App {
//  println(greeting)

  val strOption: Option[String] = None

  val a: String = strOption.getOrElse("sdfsafd")
//  val b: String = strOption.map(_)
//  println(b)

}
