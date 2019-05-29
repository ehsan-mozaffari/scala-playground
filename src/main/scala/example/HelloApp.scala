package example

import scala.util.Random

object HelloApp extends App {
  println("Hello")
  val random: BigInt = BigInt.probablePrime(100 , Random)
  println("Random: " + random)

}