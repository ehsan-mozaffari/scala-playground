import Dependencies._

name         := "scala-playground"
version      := "1.0.0"
scalaVersion := "2.13.6"
scalacOptions ++= Seq(
  "-deprecation",
  "-feature"
)

lazy val root = project in file(".")
libraryDependencies ++= lib.test.scalaTest ++ lib.typelevel.catsEffect
