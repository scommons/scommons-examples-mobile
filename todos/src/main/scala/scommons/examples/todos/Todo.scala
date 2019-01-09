package scommons.examples.todos

import io.github.shogowada.scalajs.reactjs.React
import io.github.shogowada.scalajs.reactjs.classes.ReactClass
import scommons.react.UiComponent
import scommons.reactnative._

import scala.scalajs.js

case class TodoProps(deleteTodo: Int => Unit,
                     toggleComplete: Int => Unit,
                     todo: TodoData)

object Todo extends UiComponent[TodoProps] {
  
  def apply(): ReactClass = reactClass
  lazy val reactClass: ReactClass = createComp

  private def createComp: ReactClass = React.createClass[PropsType, Unit] { self =>
    val props = self.props.wrapped
    
    <.View(^.rnStyle := styles.todoContainer)(
      <.Text(^.rnStyle := styles.todoText)(
        props.todo.title
      ),
      <.View(^.rnStyle := styles.buttons)(
        <(TodoButton())(
          ^.key := "done",
          ^.wrapped := TodoButtonProps(
            onPress = { () =>
              props.toggleComplete(props.todo.todoId)
            },
            complete = props.todo.complete,
            name = "Done"
          )
        )(),
        <(TodoButton())(
          ^.key := "delete",
          ^.wrapped := TodoButtonProps(
            onPress = { () =>
              props.deleteTodo(props.todo.todoId)
            },
            complete = false,
            name = "Delete"
          )
        )()
      )
    )
  }

  private[todos] lazy val styles = StyleSheet.create(Styles)

  private[todos] object Styles extends js.Object {
    val todoContainer: Style = new Style {
      override val marginLeft = 20
      override val marginRight = 20
      override val backgroundColor = "#ffffff"
      override val borderTopWidth = 1
      override val borderRightWidth = 1
      override val borderLeftWidth = 1
      override val borderColor = "#ededed"
      override val paddingLeft = 14
      override val paddingTop = 7
      override val paddingBottom = 7
      override val shadowOpacity = 0.2
      override val shadowRadius = 3
      override val shadowColor = "#000000"
      override val shadowOffset = new Style.ShadowOffset {
        override val width = 2
        override val height = 2
      }
      override val flexDirection = "row"
      override val alignItems = "center"
    }
    val todoText: Style = new Style {
      override val fontSize = 17
    }
    val buttons: Style = new Style {
      override val flex = 1
      override val flexDirection = "row"
      override val justifyContent = "flex-end"
      override val alignItems = "center"
    }
  }
}
