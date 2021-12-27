package rockthejvm

import cats.effect.{IO, IOApp}

import java.io.FileReader
import java.util.Scanner
import scala.concurrent.duration._

object CatsEffectIBracket extends IOApp.Simple {

  def openFileScanner(path: String): IO[Scanner] = IO(new Scanner(new FileReader(path)))

  def readLineByLine(scanner: Scanner): IO[Unit] =
    if (scanner.hasNextLine)
      IO(scanner.nextLine()) >> IO.sleep((100.millis)) >> readLineByLine(scanner)
    else
      IO.unit

  def bracketReadFile(path: String): IO[Unit] =
    IO(s"opening the file at $path") >>
      openFileScanner(path).bracket(scanner => readLineByLine(scanner))(scanner =>
        IO(s"closing file at $path") >> IO(scanner.close())
      )

  override def run: IO[Unit] = ???

}
