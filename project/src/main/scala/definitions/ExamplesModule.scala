package definitions

import common.Libs
import sbt.Keys._
import sbt._
import scommons.sbtplugin.project.CommonModule
import scommons.sbtplugin.project.CommonModule.ideExcludedDirectories

trait ExamplesModule extends CommonModule {

  override val repoName = "scommons-examples-mobile"

  val scommonsReactNativeVersion: String = Libs.scommonsReactNativeVersion

  override def definition: Project = {
    super.definition
      .settings(ExamplesModule.settings: _*)
      .settings(
        ideExcludedDirectories ++= {
          val base = baseDirectory.value
          List(
            base / "android" / "build",
            base / "ios" / "build",
            base / "node_modules"
          )
        }
      )
  }
}

object ExamplesModule {

  val settings: Seq[Setting[_]] = Seq(
    organization := "org.scommons.examples.mobile"
  )
}
