package definitions

import common.Libs
import sbt.Keys._
import sbt._
import sbtcrossproject.CrossPlugin.autoImport._
import sbtcrossproject.{CrossProject, JVMPlatform}
import scommons.sbtplugin.project.CommonModule
import scoverage.ScoverageKeys.coverageExcludedPackages

import scalajscrossproject.ScalaJSCrossPlugin.autoImport._

object StarWarsApi {

  val id: String = "scommons-examples-mobile-starwars-api"

  val base: File = file("starwars/api")

  private lazy val `scommons-examples-mobile-starwars-api`: CrossProject = CrossProject(id, base)(JSPlatform, JVMPlatform)
    .crossType(CrossType.Pure)
    .settings(CommonModule.settings: _*)
    .settings(ExamplesModule.settings: _*)
    .settings(
      coverageExcludedPackages := "scommons.examples.starwars.api.StarWarsApiClient",
      
      libraryDependencies ++= Seq(
        Libs.scommonsApiCore.value,
        Libs.scommonsApiJodaTime.value
      )
    ).jvmSettings(
      // Add JVM-specific settings here
    ).jsSettings(
      ScalaJsModule.settings ++ Seq(
        libraryDependencies ++= Seq(
          Libs.scalaJsJavaSecureRandom.value % "test"
        )
      ): _*
    )

  lazy val jvm: Project = `scommons-examples-mobile-starwars-api`.jvm

  lazy val js: Project = `scommons-examples-mobile-starwars-api`.js
}
