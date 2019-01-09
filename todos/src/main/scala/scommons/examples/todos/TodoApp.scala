package scommons.examples.todos

import io.github.shogowada.scalajs.reactjs.React
import io.github.shogowada.scalajs.reactjs.classes.ReactClass
import scommons.react.UiComponent
import scommons.reactnative._

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel(name = "TodoApp")
object TodoApp extends UiComponent[Unit] {

  private case class TodoAppState(nextTodoId: Int = 1,
                                  inputValue: String = "",
                                  todos: List[TodoData] = Nil,
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
          val todo = TodoData(self.state.nextTodoId, inputValue, complete = false)
          self.setState(s => s.copy(
            nextTodoId = s.nextTodoId + 1,
            inputValue = "",
            todos = s.todos :+ todo
          ))
        }
      }
      
      def deleteTodo(todoId: Int): Unit = {
        self.setState(s => s.copy(
          todos = s.todos.filter(_.todoId != todoId)
        ))
      }

      def toggleComplete(todoId: Int): Unit = {
        self.setState(s => s.copy(
          todos = s.todos.map {
            case t if t.todoId == todoId => t.copy(complete = !t.complete)
            case t => t
          }
        ))
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
          <(TodoList())(^.wrapped := TodoListProps(
            deleteTodo = deleteTodo,
            toggleComplete = toggleComplete,
            todos = self.state.todos
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
