name := "Scala-Applied-Exercises"

version in ThisBuild := "1.0-SNAPSHOT"

scalaVersion in ThisBuild := "2.12.6"

scalacOptions in ThisBuild += "-deprecation"

libraryDependencies in ThisBuild ++= Seq(
  "org.scalactic"  %% "scalactic"  % "3.0.4",
  "org.scalatest"  %% "scalatest"  % "3.0.4"  % Test,
  "org.scalamock"  %% "scalamock"  % "4.1.0"  % Test,
  "org.scalacheck" %% "scalacheck" % "1.13.4" % Test,
  "org.scala-lang.modules" %% "scala-java8-compat" % "0.8.0",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2",
  "com.typesafe.akka" %% "akka-actor" % "2.5.7",
  "com.typesafe.akka" %% "akka-remote" % "2.5.7",
  "com.typesafe.akka" %% "akka-testkit" % "2.5.7" % Test
)

lazy val module12 = project
lazy val module13 = project
lazy val module14 = project
lazy val module15 = project
lazy val module16 = project
