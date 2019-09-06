name := "scala-playground"

scalaVersion     := "2.12.8"
version          := "0.1.0-SNAPSHOT"

lazy val root = (project in file("."))

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test
