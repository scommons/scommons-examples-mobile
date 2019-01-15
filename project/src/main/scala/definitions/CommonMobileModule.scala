package definitions

import org.sbtidea.SbtIdeaPlugin.ideaExcludeFolders
import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import sbt.Keys._
import sbt._
import scommons.sbtplugin.project.CommonModule
//import scoverage.ScoverageKeys._

import scalajsbundler.sbtplugin.ScalaJSBundlerPlugin
import scalajsbundler.sbtplugin.ScalaJSBundlerPlugin.autoImport._

trait CommonMobileModule extends CommonModule {

//  def scommonsReactTestVersion: String
//  def scommonsMobileVersion: String

  override def definition: Project = {
    super.definition
      .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
      .settings(CommonMobileModule.settings: _*)
      .settings(
        //coverageExcludedPackages := ".*Styles",

        scalaJSUseMainModuleInitializer := false,
        webpackBundlingMode := BundlingMode.LibraryOnly()
      )
  }

  override def internalDependencies: Seq[ClasspathDep[ProjectReference]] = Nil

  override def superRepoProjectsDependencies: Seq[(String, String, Option[String])] = Seq(
//    ("scommons-mobile", "scommons-mobile-ui", None),
//    ("scommons-react-test", "scommons-react-test-renderer", Some("test"))
  )

  override def runtimeDependencies: Def.Initialize[Seq[ModuleID]] = Def.setting(Seq(
//    "org.scommons.mobile" %%% "scommons-mobile-ui" % scommonsMobileVersion
  ))

  override def testDependencies: Def.Initialize[Seq[ModuleID]] = Def.setting(Seq[ModuleID](
//    "org.scommons.react.test" %%% "scommons-react-test-renderer" % scommonsReactTestVersion
  ).map(_  % "test"))
}

object CommonMobileModule {

  val settings: Seq[Setting[_]] = Seq(
    scalaJSModuleKind := ModuleKind.CommonJSModule,
    
    //Opt-in @ScalaJSDefined by default
    scalacOptions += "-P:scalajs:sjsDefinedByDefault",
    
    // react-native DO NOT require DOM, but we enable it here only to trigger the webpack build
    // since we substitute references to react-native module with our custom react-native-mocks module
    // inside the sc-react-native-mocks.webpack.config.js
    requiresDOM in Test := true,
    
    emitSourceMaps := false,

    ideaExcludeFolders ++= {
      val base = baseDirectory.value
      List(
        s"$base/build",
        s"$base/node_modules"
      )
    },
    cleanKeepFiles ++= Seq(
      target.value / "scala-2.12" / "scalajs-bundler" / "main" / "node_modules",
      target.value / "scala-2.12" / "scalajs-bundler" / "test" / "node_modules",
      target.value / "scalajs-bundler-jsdom" / "node_modules"
    )
  )
}
