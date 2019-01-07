package scommons.examples.todos

import io.github.shogowada.scalajs.reactjs.React
import io.github.shogowada.scalajs.reactjs.classes.ReactClass
import scommons.react.UiComponent
import scommons.reactnative._

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel(name = "TodoApp")
object TodoApp extends UiComponent[Unit] {

  private case class Todo(title: String,
                          complete: Boolean)
  
  private case class TodoAppState(inputValue: String = "",
                                  todos: List[Todo] = Nil,
                                  `type`: String = "All")

  @JSExport("apply")
  def apply(): ReactClass = reactClass
  lazy val reactClass: ReactClass = createComp

  private def createComp: ReactClass = React.createClass[PropsType, TodoAppState](
    getInitialState = { _ =>
      TodoAppState()
    },
    render = { self =>
      
      def submitTodo(): Unit = {
        val inputValue = self.state.inputValue.trim
        if (inputValue.nonEmpty) {
          val todo = Todo(inputValue, complete = false)
          self.setState(s => s.copy(
            inputValue = "",
            todos = s.todos :+ todo
          ))
        }
      }
      
      <.View(^.rnStyle := styles.container)(
        <.ScrollView(^.rnStyle := styles.content, ^.keyboardShouldPersistTaps := "always")(
          <(Heading())()(),
          <(Input())(^.wrapped := InputProps(
            inputValue = self.state.inputValue,
            inputChange = { text =>
              self.setState(s => s.copy(inputValue = text))
            }
          ))(),
          <(Button())(^.wrapped := ButtonProps(submitTodo _))()
        )
      )
    }
  )

  private[todos] lazy val styles = StyleSheet.create(Styles)
  
  private[todos] object Styles extends js.Object {
    val container: Style = new Style {
      override val flex = 1
      override val backgroundColor = "#f5f5f5"
    }
    val content: Style = new Style {
      override val flex = 1
      override val paddingTop = 60
    }
  }
}
