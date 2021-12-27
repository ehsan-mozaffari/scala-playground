package rockthejvm

case class MyIO[A](unsafeRun: () => A) {
  import MyIO._

  def map[B](f: A => B): MyIO[B] = MyIO[B](() => f(unsafeRun()))

  def flatMap[B](f: A => MyIO[B]): MyIO[B] = MyIO(() => f(unsafeRun()).unsafeRun())

}

object MyIO {
  def now: MyIO[Long] = MyIO[Long](() => System.currentTimeMillis())

  def measure[A](computation: MyIO[A]): MyIO[Long] =
    for {
      startTime <- now
      _         <- computation
      endTime   <- now
    } yield endTime - startTime

}

object CatsEffectExercises extends App {

  val fiveSecComputation = MyIO { () =>
    println("starting to run MyIO")
    Thread.sleep(50000)
    println("End of the process of MyIO")
  }

  println("---------------Starting the program...")

  println(MyIO.measure(fiveSecComputation).unsafeRun())

  println("---------------Ending the program...")
}
