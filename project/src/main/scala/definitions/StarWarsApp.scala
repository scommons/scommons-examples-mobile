package definitions

import common.Libs
import sbt.Keys._
import sbt._
import scommons.sbtplugin.project.CommonMobileModule

object StarWarsApp extends ExamplesModule with CommonMobileModule {

  override val id = "scommons-examples-mobile-starwars-app"

  override val base: File = file("starwars/app")

  override def definition: Project = super.definition
    .settings(
      description := "Example StarWarsApp from the React Native in Action book, written in Scala.js"
    )

  override def internalDependencies: Seq[ClasspathDep[ProjectReference]] = Seq(
    StarWarsApi.js
  )

  override def superRepoProjectsDependencies: Seq[(String, String, Option[String])] = {
    super.superRepoProjectsDependencies ++ Seq(
      ("scommons-api", "scommons-api-dom", None),
      ("scommons-react-native", "scommons-react-navigation", None)
    )
  }
  
  override def runtimeDependencies: Def.Initialize[Seq[ModuleID]] = Def.setting {
    super.runtimeDependencies.value ++ Seq(
      Libs.scommonsApiDom.value,
      Libs.scommonsReactNavigation.value
    )
  }
  
  override def testDependencies: Def.Initialize[Seq[ModuleID]] = Def.setting {
    super.testDependencies.value ++ Seq[ModuleID](
      // specify your custom test dependencies here
    ).map(_ % "test")
  }
}
