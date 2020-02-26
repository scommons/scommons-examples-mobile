package definitions

import common.{Libs, TestLibs}
import sbt.Keys._
import sbt._
import scommons.sbtplugin.project.CommonMobileModule
import scoverage.ScoverageKeys.coverageExcludedPackages

object StarWarsApp extends ExamplesModule with CommonMobileModule {

  override val id = "scommons-examples-mobile-starwars-app"

  override val base: File = file("starwars/app")

  override def definition: Project = super.definition
    .settings(
      description := "Example StarWarsApp from the React Native in Action book, written in Scala.js",

      coverageExcludedPackages :=
        "scommons.examples.starwars.BaseStateAndRouteController" +
          ";scommons.examples.starwars.StarWarsApp" +
          ";scommons.examples.starwars.StarWarsActions"
    )

  override def internalDependencies: Seq[ClasspathDep[ProjectReference]] = Seq(
    StarWarsApi.js
  )

  override def superRepoProjectsDependencies: Seq[(String, String, Option[String])] = {
    super.superRepoProjectsDependencies ++ Seq(
      ("scommons-api", "scommons-api-dom", None),
      ("scommons-react-native", "scommons-react-navigation", None),
      ("scommons-react", "scommons-react-redux", None),

      ("scommons-react", "scommons-react-test-dom", Some("test"))
    )
  }
  
  override def runtimeDependencies: Def.Initialize[Seq[ModuleID]] = Def.setting {
    super.runtimeDependencies.value ++ Seq(
      Libs.scommonsApiDom.value,
      Libs.scommonsReactNavigation.value,
      Libs.scommonsReactRedux.value
    )
  }
  
  override def testDependencies: Def.Initialize[Seq[ModuleID]] = Def.setting {
    super.testDependencies.value ++ Seq[ModuleID](
      TestLibs.scommonsReactTestDom.value
    ).map(_ % "test")
  }
}
