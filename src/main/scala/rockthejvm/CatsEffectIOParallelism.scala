package rockthejvm

import cats.effect.{IO, IOApp}

object CatsEffectIOParallelism extends IOApp.Simple {

  //IOs is usually sequential

  override def run: IO[Unit] = ???
}
