import sbt._

object Dependencies {

  object v {
    val scalaTest  = "3.2.9"
    val catsEffect = "3.2.9"
  }

  object lib {

    object typelevel {
      val catsEffect = Seq("org.typelevel" %% "cats-effect" % v.catsEffect)
    }

    object test {
      val scalaTest = Seq("org.scalatest" %% "scalatest" % v.scalaTest)
    }
  }
}
