name := "scala-playground"

scalaVersion := "2.13.6"
version      := "0.1.0-SNAPSHOT"
scalacOptions ++= Seq("-deprecation", "-feature")

resolvers += "Artima Maven Repository".at("https://repo.artima.com/releases")

lazy val root = (project in file("."))

libraryDependencies += "org.scalatest" %% "scalatest"   % "3.2.5" % Test
libraryDependencies += "dev.zio"       %% "zio"         % "1.0.6"
libraryDependencies += "dev.zio"       %% "zio-streams" % "1.0.6"
