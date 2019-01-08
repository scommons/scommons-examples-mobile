package scommons.examples.todos

import scommons.react.test.TestSpec
import scommons.react.test.util.TestRendererUtils
import scommons.reactnative._

class TodoSpec extends TestSpec with TestRendererUtils {

  it should "render component" in {
    //given
    val props = TodoProps(TodoData(1, "test todo", complete = false))
    val component = <(Todo())(^.wrapped := props)()
    
    //when
    val result = render(component)
    
    //then
    assertNativeComponent(result,
      <("View")(^.rnStyle := Todo.styles.todoContainer)(
        <("Text")(^.rnStyle := Todo.styles.todoText)(
          props.todo.title
        )
      )
    )
  }
}
