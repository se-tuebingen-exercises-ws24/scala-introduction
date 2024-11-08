// This is an sbt build file.
// For an introduction to sbt you can look at:
//    https://www.scala-sbt.org/1.x/docs/sbt-by-example.html
//
// Build files can start out as simple as only containing one line:
//
//    scalaVersion := "3.5.1"
//
// describing the version of the programming language we use.
//
// The directory structure of an sbt project looks somewhat similar to
// a Java (Maven) project (which you might be familiar with).
//
// src/
//   main/scala       <--- Scala source files
//   test/scala       <--- Scala files that implement tests
//
// project/           <--- Configuration of sbt itself
//   build.properties <--- File that contains the sbt version
//
// build.sbt          <--- File that describes how the project is built

// The remainder of this file contains an example of a standard build
// file.
//
// Here we say that the demo project is located in the current folder
// and which settings to apply to it.
lazy val root = Project("demo", file(".")).settings(mainSettings: _*)

def mainSettings: Seq[Def.SettingsDefinition] = Seq(
  // some information about the "project" itself
  name := "demo",
  organization := "de.unituebingen",
  version := "0.10",

  // configuration telling which Scala compiler to use and some additional flags
  scalaVersion := "3.5.1",
  scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature", "-explain"),

  // other libraries that this project depends on and their version
  libraryDependencies ++= dependencies
)

// Here we can list dependencies that we want to use.
// sbt will automatically try to download them and configure everything
// for them to be usable.
//
// We cannot only use Scala libraries, but also (most) existing
// Java libraries.
def dependencies = Seq(
  "org.scalameta" %% "munit" % "0.7.29" % Test,
   // for example, here we use a library to process json config files.
   // https://github.com/spray/spray-json
  "io.spray" %% "spray-json" % "1.3.6"
)
