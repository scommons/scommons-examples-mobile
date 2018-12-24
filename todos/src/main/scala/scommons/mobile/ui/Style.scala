package scommons.mobile.ui

import io.github.shogowada.scalajs.reactjs.VirtualDOM._
import io.github.shogowada.statictags._

import scala.scalajs.js

trait Style extends js.Object {
  
  val alignItems: js.UndefOr[String] = js.undefined
  val backgroundColor: js.UndefOr[String] = js.undefined
  val color: js.UndefOr[String] = js.undefined
  val flex: js.UndefOr[Int] = js.undefined
  val fontSize: js.UndefOr[Int] = js.undefined
  val justifyContent: js.UndefOr[String] = js.undefined
  val margin: js.UndefOr[Int] = js.undefined
  val marginBottom: js.UndefOr[Int] = js.undefined
  val textAlign: js.UndefOr[String] = js.undefined
}

object Style {

  object StyleVirtualDOMAttributes {

    import VirtualDOMAttributes.Type._

    case class StyleStyleAttributeSpec(name: String) extends AttributeSpec {
      def :=(style: Style): Attribute[Style] =
        Attribute(name = name, value = style, AS_IS)
    }
  }

  implicit class StyleVirtualDOMAttributes(attributes: VirtualDOMAttributes) {

    import StyleVirtualDOMAttributes._

    lazy val rnStyle = StyleStyleAttributeSpec("style")
  }
}
