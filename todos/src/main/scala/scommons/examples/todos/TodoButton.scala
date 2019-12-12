package scommons.examples.todos

import scommons.react._
import scommons.reactnative._

import scala.scalajs.js

case class TodoButtonProps(onPress: () => Unit,
                           complete: Boolean,
                           name: String)

object TodoButton extends FunctionComponent[TodoButtonProps] {

  protected def render(compProps: Props): ReactElement = {
    val props = compProps.wrapped
    
    <.TouchableHighlight(
      ^.rnStyle := styles.button,
      ^.underlayColor := "#efefef",
      ^.onPress := props.onPress
    )(
      <.Text(^.rnStyle := js.Array(
        Some(styles.text),
        if (props.complete) Some(styles.complete) else None,
        if (props.name == "Delete") Some(styles.deleteButton) else None
      ).flatten)(
        props.name
      )
    )
  }

  private[todos] lazy val styles = StyleSheet.create(Styles)
  
  private[todos] object Styles extends js.Object {
    val button: Style = new Style {
      override val alignSelf = "flex-end"
      override val padding = 7
      override val borderColor = "#ededed"
      override val borderWidth = 1
      override val borderRadius = 4
      override val marginRight = 5
    }
    val text: Style = new TextStyle {
      override val color = "#666666"
    }
    val complete: Style = new TextStyle {
      override val color = "green"
      override val fontWeight = "bold"
    }
    val deleteButton: Style = new TextStyle {
      override val color = "rgba(175, 47, 47, 1)"
    }
  }
}
