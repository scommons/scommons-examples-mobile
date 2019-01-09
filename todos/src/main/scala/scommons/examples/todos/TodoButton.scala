package scommons.examples.todos

import io.github.shogowada.scalajs.reactjs.React
import io.github.shogowada.scalajs.reactjs.classes.ReactClass
import scommons.react.UiComponent
import scommons.reactnative._

import scala.scalajs.js

case class TodoButtonProps(onPress: () => Unit,
                           complete: Boolean,
                           name: String)

object TodoButton extends UiComponent[TodoButtonProps] {
  
  def apply(): ReactClass = reactClass
  lazy val reactClass: ReactClass = createComp

  private def createComp: ReactClass = React.createClass[PropsType, Unit] { self =>
    val props = self.props.wrapped
    
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
    val text: Style = new Style {
      override val color = "#666666"
    }
    val complete: Style = new Style {
      override val color = "green"
      override val fontWeight = "bold"
    }
    val deleteButton: Style = new Style {
      override val color = "rgba(175, 47, 47, 1)"
    }
  }
}
