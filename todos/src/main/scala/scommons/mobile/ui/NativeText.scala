package scommons.mobile.ui

import io.github.shogowada.scalajs.reactjs.VirtualDOM.VirtualDOMElements.ReactClassElementSpec
import io.github.shogowada.scalajs.reactjs.VirtualDOM._
import io.github.shogowada.scalajs.reactjs.classes.ReactClass

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("react-native", "Text")
object ReactNativeText extends ReactClass

object NativeText {

  implicit class TextVirtualDOMElements(elements: VirtualDOMElements) {
    lazy val Text: ReactClassElementSpec = elements(ReactNativeText)
  }
}
