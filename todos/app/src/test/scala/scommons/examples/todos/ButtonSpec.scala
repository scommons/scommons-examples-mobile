package scommons.examples.todos

import scommons.examples.todos.Button
import scommons.react.test._
import scommons.reactnative._

class ButtonSpec extends TestSpec with TestRendererUtils {

  it should "call submitTodo when onPress" in {
    //given
    val submitTodo = mockFunction[Unit]
    val comp = testRender(<(Button())(^.wrapped := ButtonProps(submitTodo))())
    val button = findComponents(comp, <.TouchableHighlight.reactClass).head
    
    //then
    submitTodo.expects()
    
    //when
    button.props.onPress()
  }
  
  it should "render component" in {
    //given
    val submitTodo = mockFunction[Unit]
    val component = <(Button())(^.wrapped := ButtonProps(submitTodo))()
    
    //when
    val result = testRender(component)
    
    //then
    assertNativeComponent(result,
      <.View(^.rnStyle := Button.styles.buttonContainer)(
        <.TouchableHighlight(
          ^.rnStyle := Button.styles.button,
          ^.underlayColor := "#efefef"
        )(
          <.Text(^.rnStyle := Button.styles.submit)(
            "Submit"
          )
        )
      )
    )
  }
}
