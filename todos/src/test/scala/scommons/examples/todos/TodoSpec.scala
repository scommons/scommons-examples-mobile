package scommons.examples.todos

import scommons.react.test.TestSpec
import scommons.reactnative._
import scommons.reactnative.test.util.TestRendererUtils

class TodoSpec extends TestSpec with TestRendererUtils {

  it should "call toggleComplete when onPress Done button" in {
    //given
    val deleteTodo = mockFunction[Int, Unit]
    val toggleComplete = mockFunction[Int, Unit]
    val props = TodoProps(
      deleteTodo = deleteTodo,
      toggleComplete = toggleComplete,
      todo = TodoData(1, "test todo", complete = true)
    )
    val comp = render(<(Todo())(^.wrapped := props)())
    val btnProps = findProps(comp, TodoButton).head

    //then
    toggleComplete.expects(props.todo.todoId)

    //when
    btnProps.onPress()
  }

  it should "call deleteTodo when onPress Delete button" in {
    //given
    val deleteTodo = mockFunction[Int, Unit]
    val toggleComplete = mockFunction[Int, Unit]
    val props = TodoProps(
      deleteTodo = deleteTodo,
      toggleComplete = toggleComplete,
      todo = TodoData(1, "test todo", complete = true)
    )
    val comp = render(<(Todo())(^.wrapped := props)())
    val btnProps = findProps(comp, TodoButton)(1)

    //then
    deleteTodo.expects(props.todo.todoId)

    //when
    btnProps.onPress()
  }

  it should "render component" in {
    //given
    val deleteTodo = mockFunction[Int, Unit]
    val toggleComplete = mockFunction[Int, Unit]
    val props = TodoProps(
      deleteTodo = deleteTodo,
      toggleComplete = toggleComplete,
      todo = TodoData(1, "test todo", complete = true)
    )
    val component = <(Todo())(^.wrapped := props)()
    
    //when
    val result = render(component)
    
    //then
    assertNativeComponent(result, <("View")(^.rnStyle := Todo.styles.todoContainer)(), {
      case List(text, buttons) =>
        assertNativeComponent(text,
          <("Text")(^.rnStyle := Todo.styles.todoText)(
            props.todo.title
          )
        )
        assertNativeComponent(buttons, <("View")(^.rnStyle := Todo.styles.buttons)(), {
          case List(doneBtn, deleteBtn) =>
            assertComponent(doneBtn, TodoButton) { case TodoButtonProps(_, complete, name) =>
              complete shouldBe props.todo.complete
              name shouldBe "Done"
            }
            assertComponent(deleteBtn, TodoButton) { case TodoButtonProps(_, complete, name) =>
              complete shouldBe false
              name shouldBe "Delete"
            }
        })
    })
  }
}
