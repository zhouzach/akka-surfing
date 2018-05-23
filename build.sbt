
name := "akka-surfing"

version := "0.1"

scalaVersion := "2.11.11"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4.18",
  "com.typesafe.akka" %% "akka-testkit" % "2.4.18" % Test,

  // For Akka 2.4.x and Scala 2.11.x, 2.12.x
  "com.enragedginger" %% "akka-quartz-scheduler" % "1.6.0-akka-2.4.x",
  "joda-time" % "joda-time" % "2.9.9",

  "ch.qos.logback" % "logback-classic" % "1.2.3"
)


//mainClass in Compile := Some("scala.com.eoi.EOIService")
//
//scalaSource in Compile := baseDirectory.value / "src" / "main" / "scala"
//
//scalaSource in Test := baseDirectory.value / "src" / "test" / "scala"
//
//resourceDirectory in Compile := baseDirectory.value / "src" / "main" / "resources"


enablePlugins(JavaServerAppPackaging)