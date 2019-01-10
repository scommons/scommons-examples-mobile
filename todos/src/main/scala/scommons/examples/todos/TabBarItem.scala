package scommons.examples.todos

import io.github.shogowada.scalajs.reactjs.React
import io.github.shogowada.scalajs.reactjs.classes.ReactClass
import scommons.react.UiComponent
import scommons.reactnative._

import scala.scalajs.js

case class TabBarItemProps(border: Boolean,
                           title: String,
                           selected: Boolean,
                           onPress: () => Unit)

object TabBarItem extends UiComponent[TabBarItemProps] {
  
  def apply(): ReactClass = reactClass
  lazy val reactClass: ReactClass = createComp

  private def createComp: ReactClass = React.createClass[PropsType, Unit] { self =>
    val props = self.props.wrapped
    
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
    val item: Style = new Style {
      override val flex = 1
      override val justifyContent = "center"
      override val alignItems = "center"
    }
    val border: Style = new Style {
      override val borderLeftWidth = 1
      override val borderLeftColor = "#dddddd"
    }
    val itemText: Style = new Style {
      override val color = "#777777"
      override val fontSize = 16
    }
    val selected: Style = new Style {
      override val backgroundColor = "#ffffff"
    }
    val bold: Style = new Style {
      override val fontWeight = "bold"
    }
  }
}
