package scommons.examples.todos

import scommons.react._
import scommons.reactnative._

import scala.scalajs.js

object Heading extends FunctionComponent[Unit] {

  protected def render(compProps: Props): ReactElement = {
    <.View(^.rnStyle := styles.header)(
      <.Text(^.rnStyle := styles.headerText)(
        "todos"
      )
    )
  }

  private[todos] lazy val styles = StyleSheet.create(Styles)

  private[todos] object Styles extends js.Object {
    val header: Style = new ViewStyle {
      override val marginTop = 80
    }
    val headerText: Style = new TextStyle {
      override val textAlign = "center"
      override val fontSize = 72
      override val color = "rgba(175, 47, 47, 0.25)"
      override val fontWeight = "100"
    }
  }
}
