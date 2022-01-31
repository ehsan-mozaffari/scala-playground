package rockthejvm

import cats.effect.{ExitCode, IO, IOApp}

import scala.io.StdIn

object App {

  val program =
    for {
      line <- IO(StdIn.readLine)
      _    <- IO.println(s"Your msg: $line")
    } yield ()

}

object CatsEffectIOApps extends IOApp {
  // or program.as[ExitCode.Success]
  override def run(args: List[String]): IO[ExitCode] = App.program.map(_ => ExitCode.Success)
}

object CatEfIOSimple extends IOApp.Simple {
  override def run: IO[Unit] = App.program
}
