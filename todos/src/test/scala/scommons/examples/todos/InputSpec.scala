package scommons.examples.todos

import scommons.react.test.TestSpec
import scommons.react.test.util.ShallowRendererUtils
import scommons.reactnative._

class InputSpec extends TestSpec with ShallowRendererUtils {

  it should "call inputChange when onChangeText" in {
    //given
    val inputChange = mockFunction[String, Unit]
    val props = InputProps(inputValue = "test value", inputChange = inputChange)
    val comp = shallowRender(<(Input())(^.wrapped := props)())
    val input = findComponents(comp, NativeTextInput).head
    val text = "undated text"
    
    //then
    inputChange.expects(text)
    
    //when
    input.props.onChangeText(text)
  }
  
  it should "render component" in {
    //given
    val inputChange = mockFunction[String, Unit]
    val props = InputProps(inputValue = "test value", inputChange = inputChange)
    val component = <(Input())(^.wrapped := props)()
    
    //when
    val result = shallowRender(component)
    
    //then
    assertNativeComponent(result,
      <("View")(^.rnStyle := Input.styles.inputContainer)(
        <("TextInput")(
          ^.rnStyle := Input.styles.input,
          ^.value := props.inputValue,
          ^.placeholder := "What needs to be done?",
          ^.placeholderTextColor := "#CACACA",
          ^.selectionColor := "#666666"
        )()
      )
    )
  }
}
