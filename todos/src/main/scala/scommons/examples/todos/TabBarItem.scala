package scommons.examples.todos

import scommons.react._
import scommons.reactnative._

import scala.scalajs.js

case class TabBarItemProps(border: Boolean,
                           title: String,
                           selected: Boolean,
                           onPress: () => Unit)

object TabBarItem extends FunctionComponent[TabBarItemProps] {

  protected def render(compProps: Props): ReactElement = {
    val props = compProps.wrapped
    
    <.TouchableHighlight(
      ^.rnStyle := js.Array(
        Some(styles.item),
        if (props.selected) Some(styles.selected) else None,
        if (props.border) Some(styles.border) else None
      ).flatten,
      ^.underlayColor := "#efefef",
      ^.onPress := props.onPress
    )(
      <.Text(^.rnStyle := js.Array(
        Some(styles.itemText),
        if (props.selected) Some(styles.bold) else None
      ).flatten)(
        props.title
      )
    )
  }

  private[todos] lazy val styles = StyleSheet.create(Styles)
  
  private[todos] object Styles extends js.Object {
    val item: Style = new ViewStyle {
      override val flex = 1
      override val justifyContent = "center"
      override val alignItems = "center"
    }
    val border: Style = new Style {
      override val borderLeftWidth = 1
      override val borderLeftColor = "#dddddd"
    }
    val itemText: Style = new TextStyle {
      override val color = "#777777"
      override val fontSize = 16
    }
    val selected: Style = new Style {
      override val backgroundColor = "#ffffff"
    }
    val bold: Style = new TextStyle {
      override val fontWeight = "bold"
    }
  }
}
