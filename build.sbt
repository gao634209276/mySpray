name := "mySpray"

version := "0.1"

scalaVersion := "2.11.4"


val akka = "2.4.9"

val spray = "1.3.3"

resolvers += Resolver.url("TypeSafe Ivy releases", url("http://dl.bintray.com/typesafe/ivy-releases/"))(Resolver.ivyStylePatterns)

libraryDependencies ++=
  Seq(
    // -- Logging --
    "ch.qos.logback" % "logback-classic" % "1.1.2",
    "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2",
    // -- Akka --
    "com.typesafe.akka" %% "akka-testkit" % akka % "test",
    "com.typesafe.akka" %% "akka-actor" % akka,
    "com.typesafe.akka" %% "akka-slf4j" % akka,
    // -- Spray --
    "io.spray" %% "spray-routing" % spray,
    "io.spray" %% "spray-client" % spray,
    "io.spray" %% "spray-testkit" % spray % "test",
    // -- json --
    "io.spray" %% "spray-json" % "1.3.2",
    // -- config --
    "com.typesafe" % "config" % "1.2.1",
    // -- testing --
    "org.scalatest" %% "scalatest" % "2.2.1" % "test"
  )

//lazy val root = (project in file(".")).enablePlugins(JavaAppPackaging)

//addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.0.0")
