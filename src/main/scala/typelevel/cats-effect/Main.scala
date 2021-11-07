package typelevel.cats.effect
import cats.effect._

object HelloWorld extends IOApp.Simple {
  val run = IO.print("Hello ") >> IO.print("World!")
}
