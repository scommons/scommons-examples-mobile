package scommons.examples.todos

import io.github.shogowada.scalajs.reactjs.React
import io.github.shogowada.scalajs.reactjs.classes.ReactClass
import scommons.react.UiComponent
import scommons.reactnative._

case class TodoListProps(deleteTodo: Int => Unit,
                         toggleComplete: Int => Unit,
                         todos: List[TodoData])

object TodoList extends UiComponent[TodoListProps] {
  
  def apply(): ReactClass = reactClass
  lazy val reactClass: ReactClass = createComp

  private def createComp: ReactClass = React.createClass[PropsType, Unit] { self =>
    val props = self.props.wrapped
    
    <.View()(
      props.todos.map { todo =>
        <(Todo())(
          ^.key := s"${todo.todoId}",
          ^.wrapped := TodoProps(
            deleteTodo = props.deleteTodo,
            toggleComplete = props.toggleComplete,
            todo = todo
          )
        )()
      }
    )
  }
}
