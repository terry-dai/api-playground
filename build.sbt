organization := "api"

name := "api-playground"

version := "1.0"

scalaVersion := "2.12.4"


val http4sVersion = "0.18.3"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "1.0.1",

  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "org.http4s" %% "http4s-blaze-server" % http4sVersion,
  "org.http4s" %% "http4s-blaze-client" % http4sVersion
)

scalacOptions ++= Seq("-Ypartial-unification")