package rockthejvm

import cats.effect.kernel.Outcome.{Canceled, Errored, Succeeded}
import cats.effect.{IO, IOApp}

import scala.concurrent.duration.FiniteDuration

object CatsEffectFibers extends IOApp.Simple {
  override def run: IO[Unit] = ???

  def processResultFromFiber[A](io: IO[A]): IO[A] =
    (for {
      fib    <- io.start
      result <- fib.join
    } yield result match {
      case Succeeded(result) => result
      case Errored(e)        => IO.raiseError(e)
      case Canceled()        => IO.raiseError(new RuntimeException("Cancelled exception"))
    }).flatten

  def tupleIOs[A, B](ioa: IO[A], iob: IO[B]): IO[(A, B)] =
    (for {
      fibA    <- ioa.start
      fibB    <- iob.start
      resultA <- fibA.join
      resultB <- fibB.join
    } yield (resultA, resultB) match {
      case (Succeeded(succA), Succeeded(succB)) => succA.flatMap(a => succB.map(b => (a, b)))
      case (Errored(eA), _)                     =>
        fibB.cancel
        IO.raiseError(eA)
      case (_, Errored(eB))                     =>
        fibA.cancel
        IO.raiseError(eB)
      case (Succeeded(sa), Canceled()) => ???
      case (Canceled(), Succeeded(sb)) => ???
      case (Canceled(), Canceled()) => IO.raiseError(new RuntimeException(""))
    }).flatten

  def timeout[A](io: IO[A], duration: FiniteDuration): IO[A] =
    (for {
      fibIO         <- io.start
      fibTimeout    <- IO.sleep(duration).start
      resultIO      <- fibIO.join
      resultTimeout <- fibTimeout.join
    } yield (resultIO, resultTimeout) match {
      case (r, Succeeded(_)) =>
        r match {
          case Succeeded(v) => v
          case Errored(e)   => IO.raiseError(e)
          case Canceled()   => IO.raiseError(new RuntimeException("fibIo is cancelled by another process"))
        }
      case (_, _)            =>
        fibIO.cancel
        fibTimeout.cancel
        IO.raiseError(new RuntimeException("cancelled by timeout"))
    }).flatten

}
