package definitions

import common.{Libs, TestLibs}
import sbt.Keys._
import sbt._

import scalajsbundler.sbtplugin.ScalaJSBundlerPlugin.autoImport._

object ExamplesTodos extends ExamplesModule with CommonMobileModule {

  override val id = "scommons-examples-mobile-todos"

  override val base: File = file("todos")

  override def definition: Project = super.definition
    .settings(
      npmDependencies in Compile ++= Seq(
        "react" -> "16.6.3",
        "react-dom" -> "16.6.3"
        //"react-native" -> "0.57.8"
      ),
      npmResolutions in Compile := Map(
        "react" -> "16.6.3",
        "react-dom" -> "16.6.3"
      ),
      
      // test settings
      npmResolutions in Test := Map(
        "react" -> "16.6.3",
        "react-dom" -> "16.6.3",
        "react-test-renderer" -> "16.6.3"
      ),
      npmDependencies in Test ++= Seq(
        "react-test-renderer" -> "16.6.3"
      ),
      webpackConfigFile in Test := Some(
        baseDirectory.value / "src" / "test" / "resources" / "test.webpack.config.js"
      )
    )

  override val superRepoProjectsDependencies: Seq[(String, String, Option[String])] = Seq(
    ("scommons-react", "scommons-react-core", None),
    ("scommons-react-native", "scommons-react-native-core", None),
    
    ("scommons-react", "scommons-react-test", Some("test")),
    ("scommons-react-native", "scommons-react-native-test", Some("test"))
  )

  override def runtimeDependencies: Def.Initialize[Seq[ModuleID]] = Def.setting {
    super.runtimeDependencies.value ++ Seq(
      // specify your custom runtime dependencies here
      Libs.scommonsReactNativeCore.value
    )
  }
  
  override def testDependencies: Def.Initialize[Seq[ModuleID]] = Def.setting {
    super.testDependencies.value ++ Seq[ModuleID](
      // specify your custom test dependencies here
      TestLibs.scommonsReactNativeTest.value
    ).map(_ % "test")
  }
}
