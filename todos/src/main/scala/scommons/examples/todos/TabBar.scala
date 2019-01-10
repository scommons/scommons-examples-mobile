package scommons.examples.todos

import io.github.shogowada.scalajs.reactjs.React
import io.github.shogowada.scalajs.reactjs.classes.ReactClass
import scommons.react.UiComponent
import scommons.reactnative._

import scala.scalajs.js

case class TabBarProps(setType: TodoType => Unit,
                       `type`: TodoType)

object TabBar extends UiComponent[TabBarProps] {
  
  def apply(): ReactClass = reactClass
  lazy val reactClass: ReactClass = createComp

  private def createComp: ReactClass = React.createClass[PropsType, Unit] { self =>
    val props = self.props.wrapped
    
    <.View(^.rnStyle := styles.container)(
      <(TabBarItem())(^.key := "all", ^.wrapped := TabBarItemProps(
        border = false,
        title = "All",
        selected = props.`type` == TodoType.All,
        onPress = { () =>
          props.setType(TodoType.All)
        }
      ))(),
      <(TabBarItem())(^.key := "active", ^.wrapped := TabBarItemProps(
        border = true,
        title = "Active",
        selected = props.`type` == TodoType.Active,
        onPress = { () =>
          props.setType(TodoType.Active)
        }
      ))(),
      <(TabBarItem())(^.key := "complete", ^.wrapped := TabBarItemProps(
        border = true,
        title = "Complete",
        selected = props.`type` == TodoType.Complete,
        onPress = { () =>
          props.setType(TodoType.Complete)
        }
      ))()
    )
  }

  private[todos] lazy val styles = StyleSheet.create(Styles)
  
  private[todos] object Styles extends js.Object {
    val container: Style = new Style {
      override val height = 70
      override val flexDirection = "row"
      override val borderTopWidth = 1
      override val borderTopColor = "#dddddd"
    }
  }
}
