import Dependencies._

scalaVersion     := "2.12.8"
version          := "0.1.0-SNAPSHOT"
organization     := "com.example"
organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "scala-playground",
    libraryDependencies += scalaTest % Test
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
