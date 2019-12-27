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

  private[todos] lazy val styles = StyleSheet.create(new Styles)
  private[todos] class Styles extends js.Object {
    import Style._
    import TextStyle._
    import ViewStyle._
    
    val button: Style = new ViewStyle {
      override val alignSelf = AlignSelf.`flex-end`
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
      override val color = Color.green
      override val fontWeight = FontWeight.bold
    }
    val deleteButton: Style = new TextStyle {
      override val color = "rgba(175, 47, 47, 1)"
    }
  }
}
