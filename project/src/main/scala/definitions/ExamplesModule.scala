package definitions

import org.sbtidea.SbtIdeaPlugin.ideaExcludeFolders
import sbt.Keys._
import sbt._
import scommons.sbtplugin.project.CommonModule

trait ExamplesModule extends CommonModule {

  override val repoName = "scommons-examples-mobile"

  override def definition: Project = {
    super.definition
      .settings(ExamplesModule.settings: _*)
      .settings(
        ideaExcludeFolders ++= {
          val base = baseDirectory.value
          List(
            s"$base/android/build",
            s"$base/ios/build",
            s"$base/node_modules"
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
