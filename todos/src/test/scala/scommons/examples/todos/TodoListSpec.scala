package scommons.examples.todos

import org.scalatest.Succeeded
import scommons.react.test.TestSpec
import scommons.react.test.util.TestRendererUtils

class TodoListSpec extends TestSpec with TestRendererUtils {

  it should "render component" in {
    //given
    val props = TodoListProps(List(
      TodoData(1, "todo 1", complete = false),
      TodoData(2, "todo 2", complete = true)
    ))
    val component = <(TodoList())(^.wrapped := props)()
    
    //when
    val result = render(component)
    
    //then
    assertNativeComponent(result, <("View")()(), { todos =>
      todos.size shouldBe props.todos.size
      todos.zip(props.todos).foreach { case (todoElem, expectedTodo) =>
        assertComponent(todoElem, Todo) { case TodoProps(todo) =>
          todo shouldBe expectedTodo
        }
      }
      
      Succeeded
    })
  }
}
