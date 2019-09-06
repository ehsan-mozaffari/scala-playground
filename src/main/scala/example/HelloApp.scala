package example

object HelloApp extends App {
  println("These two are the same")
  println("App has main method")
}

object HelloAppWithMain {

  def main(args: Array[String]): Unit = {
    println("These two are the same")
  }
}