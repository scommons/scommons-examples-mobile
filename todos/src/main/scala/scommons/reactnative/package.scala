package scommons

import io.github.shogowada.scalajs.reactjs.VirtualDOM
import io.github.shogowada.scalajs.reactjs.VirtualDOM.VirtualDOMElements.ReactClassElementSpec
import io.github.shogowada.scalajs.reactjs.VirtualDOM._
import io.github.shogowada.scalajs.reactjs.classes.ReactClass
import io.github.shogowada.statictags._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

package object reactnative {

  @js.native
  @JSImport("react-native", "View")
  object NativeView extends ReactClass

  @js.native
  @JSImport("react-native", "Text")
  object NativeText extends ReactClass

  implicit class ViewVirtualDOMElements(elements: VirtualDOMElements) {
    lazy val View: ReactClassElementSpec = elements(NativeView)
  }

  implicit class TextVirtualDOMElements(elements: VirtualDOMElements) {
    lazy val Text: ReactClassElementSpec = elements(NativeText)
  }

  object ReactNativeVirtualDOMAttributes {

    import VirtualDOMAttributes.Type._

    case class ReactNativeStyleAttributeSpec(name: String) extends AttributeSpec {
      def :=(style: Style): Attribute[Style] =
        Attribute(name = name, value = style, AS_IS)
    }
  }

  implicit class StyleVirtualDOMAttributes(attributes: VirtualDOMAttributes) {

    import ReactNativeVirtualDOMAttributes._

    lazy val rnStyle = ReactNativeStyleAttributeSpec("style")
  }

  lazy val < : VirtualDOM.VirtualDOMElements = VirtualDOM.<
  lazy val ^ : VirtualDOM.VirtualDOMAttributes = VirtualDOM.^
}
