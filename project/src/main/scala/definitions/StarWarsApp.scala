package definitions

import common.Libs
import sbt.Keys._
import sbt._
import scoverage.ScoverageKeys.coverageExcludedPackages

object StarWarsApp extends ScalaJsModule {

  override val id = "scommons-examples-mobile-starwars-app"

  override val base: File = file("starwars/app")

  override def definition: Project = super.definition
    .settings(
      description := "Example StarWarsApp from the React Native in Action book, written in Scala.js",

      coverageExcludedPackages :=
        "scommons.examples.starwars.StarWarsApp" +
          ";scommons.examples.starwars.StarWarsActions"
    )

  override def internalDependencies: Seq[ClasspathDep[ProjectReference]] = Seq(
    StarWarsApi.js
  )

  override def superRepoProjectsDependencies: Seq[(String, String, Option[String])] = {
    super.superRepoProjectsDependencies ++ Seq(
      ("scommons-react-native", "scommons-react-native-ui", None)
    )
  }
  
  override def runtimeDependencies: Def.Initialize[Seq[ModuleID]] = Def.setting {
    super.runtimeDependencies.value ++ Seq(
      Libs.scommonsReactNativeUi.value
    )
  }
  
  override def testDependencies: Def.Initialize[Seq[ModuleID]] = Def.setting {
    super.testDependencies.value ++ Seq[ModuleID](
      // specify your custom test dependencies here
    ).map(_ % "test")
  }
}
