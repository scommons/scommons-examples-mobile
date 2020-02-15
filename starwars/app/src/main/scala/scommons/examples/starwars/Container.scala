package scommons.examples.starwars

import scommons.react._
import scommons.reactnative.Style._
import scommons.reactnative._

import scala.scalajs.js

object Container extends FunctionComponent[Unit] {

  protected def render(props: Props): ReactElement = {
    <.View(^.rnStyle := styles.container)(
      props.children
    )
  }

  private[starwars] lazy val styles = StyleSheet.create(new Styles)
  private[starwars] class Styles extends js.Object {
    
    val container: Style = new ViewStyle {
      override val flex = 1
      override val backgroundColor = Color.black
    }
  }
}
