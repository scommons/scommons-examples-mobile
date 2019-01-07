package scommons.reactnative

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("react-native", "StyleSheet")
object ReactNativeStyleSheet extends js.Object {

  def create(obj: js.Object): js.Object = js.native
}

object StyleSheet {

  def create[S <: js.Object](obj: S): S = {
    ReactNativeStyleSheet.create(obj).asInstanceOf[S]
  }
}
