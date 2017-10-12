organization := "in.norbor"

name := "play-seed"

version := "1.0"

sources in(Compile, doc) := Seq.empty

publishArtifact in(Compile, packageDoc) := false

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.3"

scalacOptions := Seq("-feature", "-deprecation")

routesGenerator := InjectedRoutesGenerator

libraryDependencies ++= Seq(
  guice
  , "com.fasterxml.jackson.module" % "jackson-module-scala_2.12" % "2.9.1"
  , "com.typesafe.scala-logging" %% "scala-logging" % "3.7.1"
  , "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.1" % Test
)

import com.typesafe.sbt.packager.universal.ZipHelper

packageBin in Universal := {
  val originalFileName = (packageBin in Universal).value
  val (base, ext) = originalFileName.baseAndExt
  val newFileName = file(originalFileName.getParent) / (base + "_dist." + ext)
  val extractedFiles = IO.unzip(originalFileName, file(originalFileName.getParent))
  val mappings: Set[(File, String)] = extractedFiles.map(f => (f, f.getAbsolutePath.substring(originalFileName.getParent.length + base.length + 2)))
  val binFiles = mappings.filter { case (file, path) => path.startsWith("bin/") }
  for (f <- binFiles) f._1.setExecutable(true)
  ZipHelper.zip(mappings, newFileName)
  IO.move(newFileName, originalFileName)
  IO.delete(file(originalFileName.getParent + "/" + originalFileName.base))
  originalFileName
}
