name := "BigHassle"

version := "1.0.0"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "org.webjars" %% "webjars-play" % "2.3.0-2",
  "org.webjars" % "bootstrap" % "3.1.1-2",
  "com.typesafe.slick" %% "slick" % "2.1.0"
)

lazy val root = (project in file(".")).enablePlugins(PlayScala)


