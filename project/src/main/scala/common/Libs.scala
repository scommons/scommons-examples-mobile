package common

import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import sbt._
import scommons.sbtplugin.project.CommonLibs

object Libs extends CommonLibs {
  
  val scommonsReactNativeVersion = "0.1.0-SNAPSHOT"

  lazy val scommonsReactNativeCore = Def.setting("org.scommons.react-native" %%% "scommons-react-native-core" % scommonsReactNativeVersion)
}
