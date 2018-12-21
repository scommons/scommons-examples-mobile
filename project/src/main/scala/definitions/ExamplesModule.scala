package definitions

import common.Libs
import sbt.Keys._
import sbt._
import scommons.sbtplugin.project.CommonModule

trait ExamplesModule extends CommonModule {

  override val repoName = "scommons-examples-mobile"

  override def definition: Project = {
    super.definition
      .settings(ExamplesModule.settings: _*)
  }
}

object ExamplesModule {

  val settings: Seq[Setting[_]] = Seq(
    organization := "org.scommons.examples.mobile"
  )
}
