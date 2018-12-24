package scommons.mobile.ui

import io.github.shogowada.scalajs.reactjs.VirtualDOM.VirtualDOMElements.ReactClassElementSpec
import io.github.shogowada.scalajs.reactjs.VirtualDOM._
import io.github.shogowada.scalajs.reactjs.classes.ReactClass

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("react-native", "View")
object ReactNativeView extends ReactClass

object NativeView {

  implicit class ViewVirtualDOMElements(elements: VirtualDOMElements) {
    lazy val View: ReactClassElementSpec = elements(ReactNativeView)
  }
}
