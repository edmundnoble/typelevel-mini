import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import org.scalajs.sbtplugin.cross.CrossProject
import org.scalajs.sbtplugin.{AbstractJSDep, ScalaJSPlugin}
import sbt.Keys._
import sbt._
import sbt.complete.Parser
//import scoverage.ScoverageKeys.coverageExcludedPackages

object AtlastBuild {

val baseSettings = Seq(
    version := "0.0.1",
    scalaVersion := "2.12.1",
    scalaOrganization := "org.typelevel",
    updateOptions ~= (_.withCachedResolution(true)),
    scalacOptions ++= Seq(
      "-Xexperimental"
    ),
    Dependencies.kindProjector
  )

  lazy val core: CrossProject =  crossProject.in(file("core"))
    .settings(baseSettings: _*)
    .jsSettings(ScalaJSPlugin.projectSettings: _*)
    .settings(Dependencies.cats: _*)
    .settings(Dependencies.scalatest: _*)

  lazy val coreJVM = core.jvm
  lazy val coreJS = core.js

  lazy val atlast: Project = project.in(file("."))
    .aggregate(coreJVM, coreJS)
    .settings(Defaults.projectCore)
    .settings(baseSettings: _*)

}
