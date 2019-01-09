package scommons.examples.todos

import org.scalatest.Succeeded
import scommons.react.test.TestSpec
import scommons.react.test.util.TestRendererUtils

class TodoListSpec extends TestSpec with TestRendererUtils {

  it should "render component" in {
    //given
    val deleteTodo = mockFunction[Int, Unit]
    val toggleComplete = mockFunction[Int, Unit]
    val props = TodoListProps(
      deleteTodo = deleteTodo,
      toggleComplete = toggleComplete,
      todos = List(
        TodoData(1, "todo 1", complete = false),
        TodoData(2, "todo 2", complete = true)
      )
    )
    val component = <(TodoList())(^.wrapped := props)()
    
    //when
    val result = render(component)
    
    //then
    assertNativeComponent(result, <("View")()(), { todos =>
      todos.size shouldBe props.todos.size
      todos.zip(props.todos).foreach { case (todoElem, expectedTodo) =>
        assertComponent(todoElem, Todo) { case TodoProps(resDeleteTodo, resToggleComplete, todo) =>
          resDeleteTodo shouldBe deleteTodo
          resToggleComplete shouldBe toggleComplete
          todo shouldBe expectedTodo
        }
      }
      
      Succeeded
    })
  }
}
