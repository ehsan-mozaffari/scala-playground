package typelevel.cats.effect

import cats.effect.IOApp
import cats.effect.IO

object Playground extends IOApp.Simple{

  override def run: IO[Unit] = IO.println("Hello world! I am cats effect.")
  
}
 