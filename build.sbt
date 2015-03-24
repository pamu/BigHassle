name := "BigHassle"

version := "1.0.0"

scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
  "org.webjars" %% "webjars-play" % "2.3.0-2",
  "org.webjars" % "bootstrap" % "3.1.1-2"
)

lazy val root = (project in file(".")).enablePlugins(PlayScala)


