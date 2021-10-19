import sbt._

object Dependencies {

  object v {
    val scalaTest = "3.2.9"
  }

  object lib {

    object test {
      val scalaTest = Seq("org.scalatest" %% "scalatest" % v.scalaTest)
    }
  }
}
