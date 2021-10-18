import Dependencies._
name := "scala-playground"

scalaVersion := "2.13.6"
version      := "0.1.0-SNAPSHOT"
scalacOptions ++= Seq(
  "-deprecation",
  "-feature"
)

resolvers += "Artima Maven Repository".at("https://repo.artima.com/releases")

lazy val root = project in file(".")
libraryDependencies ++= lib.config.pureConfig ++ lib.test.scalaTest
