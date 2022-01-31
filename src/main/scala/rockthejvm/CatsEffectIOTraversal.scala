package rockthejvm

import cats.Traverse
import cats.effect.{IO, IOApp}

object CatsEffectIOTraversal extends IOApp.Simple {

  def sequence[A](listOfIOs: List[IO[A]]):                    IO[List[A]] = Traverse[List].traverse(listOfIOs)(identity)
  def sequence_v2[F[_]: Traverse, A](wrapperOfIOs: F[IO[A]]): IO[F[A]]    = Traverse[F].traverse(wrapperOfIOs)(identity)

  import cats.syntax.parallel._
  def parSequence[A](listOfIOs: List[IO[A]]):                    IO[List[A]] = listOfIOs.parTraverse(identity)
  def parSequence_v2[F[_]: Traverse, A](wrapperOfIOs: F[IO[A]]): IO[F[A]]    = wrapperOfIOs.parTraverse(identity)

  override def run: IO[Unit] = ???

}
