import sbt._

object Dependencies {

  object v {
    val scalaTest  = "3.2.10"
    val catsEffect = "3.2.9"
    val catsEffectTestingScalaTest = "1.0.0-M1"
  }

  object lib {

    object typelevel {
      // The core of cats effect 
      val catsEffect = Seq("org.typelevel" %% "cats-effect" % v.catsEffect)
      // cats effect unit testing for scalatest
      val catsEffectTestingScalaTest= Seq("com.codecommit" %% "cats-effect-testing-scalatest" % v.catsEffectTestingScalaTest % Test withSources() withJavadoc())

    }

    object test {
      // scala test for testing scala app
      val scalaTest = Seq("org.scalatest" %% "scalatest" % v.scalaTest)
    }
  }
}

