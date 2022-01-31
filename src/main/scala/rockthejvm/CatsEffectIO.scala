package rockthejvm

import cats.effect.IO

import scala.util.{Failure, Success, Try}

object CatsEffectIO extends App {
  def sequenceTakeLast[A, B](ioa: IO[A], iob: IO[B]): IO[B] = ioa.flatMap(_ => iob)

  def sequenceTakeFirst[A, B](ioa: IO[A], iob: IO[B]): IO[A] = ioa.flatMap(a => iob.map(_ => a))

  def forever[A](io: IO[A]): IO[A] = io.flatMap(_ => forever(io))

  def convert[A, B](ioa: IO[A], value: B): IO[B] = ioa.map(a => value)

  def asUnit[A](ioa: IO[A]): IO[Unit] = ioa.map(_ => ())

  def sum(n: Int): IO[Int] =
    if (n <= 0)
      IO.pure(0)
    else
      IO.pure(n).flatMap(n => sum(n - 1).map(someNmin => n + someNmin))

  def fibonacci(n: Int): IO[BigInt] =
    if (n < 2)
      IO(1)
    else
      for {
        last <- IO(fibonacci(n - 1)).flatMap(x => x)
        prev <- IO(fibonacci(n - 2)).flatten
      } yield last + prev

  def fibonacciV2(n: Int): IO[BigInt] =
    if (n < 2)
      IO(1)
    else
      for {
        last <- IO.delay(fibonacciV2(n - 1)).flatten
        prev <- IO.defer(fibonacciV2(n - 2)) // Like last
      } yield last + prev

  // println(s"Sum of ${sum(50).unsafeRunSync()}")

  def option2IO[A](option: Option[A])(ifEmpty: Throwable):   IO[A] = option.map(IO.pure).getOrElse(IO.raiseError(ifEmpty))
  def option2IOV2[A](option: Option[A])(ifEmpty: Throwable): IO[A] = IO.fromOption(option)(ifEmpty)

  def try2IO[A](aTry: Try[A]): IO[A] =
    aTry match {
      case Success(value)     => IO.pure(value)
      case Failure(exception) => IO.raiseError(exception)
    }

  def try2IOV2[A](aTry: Try[A]): IO[A] = IO.fromTry(aTry)

  def either2IO[A](anEither: Either[Throwable, A]): IO[A] =
    anEither match {
      case Right(value)    => IO.pure(value)
      case Left(exception) => IO.raiseError(exception)
    }

  def either2IOV2[A](anEither: Either[Throwable, A]): IO[A] = IO.fromEither(anEither)

  def handleIOError[A](io: IO[A])(handler: Throwable => A):         IO[A] = io.redeem(handler, v => v)
  def handleIOErrorV2[A](io: IO[A])(handler: Throwable => A):       IO[A] = io.redeem(handler, identity) // identity = v => v
  def handleIOErrorWith[A](io: IO[A])(handler: Throwable => IO[A]): IO[A] = io.redeemWith(handler, v => IO.pure(v))

}
