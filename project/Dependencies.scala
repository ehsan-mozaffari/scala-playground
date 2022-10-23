import sbt._

object Dependencies {

  object v {
    val scalaTest                  = "3.2.10"
    val catsCore                   = "2.8.0"
    val catsScalaTest              = "3.0.5"
    val catsEffect                 = "3.2.9"
    val catsEffectTestingScalaTest = "1.0.0-M1"
    val jodaTime                   = "2.10.13"
  }

  object lib {

    object typelevel {
      // The core of cats - %%% is for scala.js or Scala-Native libraries
      val catsCore:   Seq[ModuleID] = Seq("org.typelevel" %% "cats-core" % v.catsCore)
      // The core of cats effect
      val catsEffect: Seq[ModuleID] = Seq("org.typelevel" %% "cats-effect" % v.catsEffect)

      // cats effect unit testing for scalatest
      val catsEffectTestingScalaTest: Seq[ModuleID] = Seq(
        "com.codecommit" %% "cats-effect-testing-scalatest" % v.catsEffectTestingScalaTest % Test withSources () withJavadoc ()
      )

    }

    object test {
      // Scalatest bindings for cats
      val catsScalaTest: Seq[ModuleID] = Seq("com.ironcorelabs" %% "cats-scalatest" % v.catsScalaTest % Test)
      // scala test for testing scala app
      val scalaTest:     Seq[ModuleID] = Seq("org.scalatest" %% "scalatest" % v.scalaTest)
    }

    object time {
      val jodaTime: Seq[ModuleID] = Seq("joda-time" % "joda-time" % v.jodaTime)
    }

  }

}
