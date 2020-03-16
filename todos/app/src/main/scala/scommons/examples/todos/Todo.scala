package scommons.examples.todos

import scommons.react._
import scommons.reactnative._

import scala.scalajs.js

case class TodoProps(deleteTodo: Int => Unit,
                     toggleComplete: Int => Unit,
                     todo: TodoData)

object Todo extends FunctionComponent[TodoProps] {

  protected def render(compProps: Props): ReactElement = {
    val props = compProps.wrapped
    
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

  private[todos] lazy val styles = StyleSheet.create(new Styles)
  private[todos] class Styles extends js.Object {
    import Style._
    import ViewStyle._
    
    val todoContainer: Style = new ViewStyle {
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
      override val shadowOffset = new ShadowOffset {
        override val width = 2
        override val height = 2
      }
      override val flexDirection = FlexDirection.row
      override val alignItems = AlignItems.center
    }
    val todoText: Style = new TextStyle {
      override val fontSize = 17
    }
    val buttons: Style = new ViewStyle {
      override val flex = 1
      override val flexDirection = FlexDirection.row
      override val justifyContent = JustifyContent.`flex-end`
      override val alignItems = AlignItems.center
    }
  }
}
