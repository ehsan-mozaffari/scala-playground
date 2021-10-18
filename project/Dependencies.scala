import sbt._

object Dependencies {

  object v {
    val pureConfig = "0.17.0"
    val scalaTest  = "3.2.9"
  }

  object lib {

    object config {
      // Pureconfig - A library for loading configuration files in a pure way
      val pureConfig = Seq("com.github.pureconfig" %% "pureconfig" % v.pureConfig)
    }

    object test {
      val scalaTest = Seq("org.scalatest" %% "scalatest" % v.scalaTest)
    }
  }
}
