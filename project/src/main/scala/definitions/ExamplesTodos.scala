package definitions

import common.{Libs, TestLibs}
import sbt._

import scalajsbundler.sbtplugin.ScalaJSBundlerPlugin.autoImport._

object ExamplesTodos extends ExamplesModule with CommonMobileModule {

  override val id = "scommons-examples-mobile-todos"

  override val base: File = file("todos")

  override def definition: Project = super.definition
    .settings(
      npmDependencies in Test ++= Seq(
        "react-test-renderer" -> "15.6.1"
      )
    )

  override def runtimeDependencies: Def.Initialize[Seq[ModuleID]] = Def.setting {
    super.runtimeDependencies.value ++ Seq(
      // specify your custom runtime dependencies here
      Libs.sjsReactJs.value
    )
  }
  
  override def testDependencies: Def.Initialize[Seq[ModuleID]] = Def.setting {
    super.testDependencies.value ++ Seq[ModuleID](
      // specify your custom test dependencies here
      TestLibs.scalaTestJs.value,
      TestLibs.scalaMockJs.value
    ).map(_ % "test")
  }
}
