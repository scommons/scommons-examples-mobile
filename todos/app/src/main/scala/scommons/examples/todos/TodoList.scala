package scommons.examples.todos

import scommons.react._
import scommons.reactnative._

case class TodoListProps(deleteTodo: Int => Unit,
                         toggleComplete: Int => Unit,
                         todos: List[TodoData])

object TodoList extends FunctionComponent[TodoListProps] {

  protected def render(compProps: Props): ReactElement = {
    val props = compProps.wrapped
    
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
