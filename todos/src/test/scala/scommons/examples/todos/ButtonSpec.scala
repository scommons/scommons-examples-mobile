package scommons.examples.todos

import scommons.react.test.TestSpec
import scommons.react.test.util.TestRendererUtils
import scommons.reactnative._

class ButtonSpec extends TestSpec with TestRendererUtils {

  it should "call submitTodo when onPress" in {
    //given
    val submitTodo = mockFunction[Unit]
    val comp = render(<(Button())(^.wrapped := ButtonProps(submitTodo))())
    val button = findComponents(comp, NativeTouchableHighlight).head
    
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
    val result = render(component)
    
    //then
    assertNativeComponent(result,
      <("View")(^.rnStyle := Button.styles.buttonContainer)(
        <("TouchableHighlight")(
          ^.rnStyle := Button.styles.button,
          ^.underlayColor := "#efefef"
        )(
          <("Text")(^.rnStyle := Button.styles.submit)(
            "Submit"
          )
        )
      )
    )
  }
}
