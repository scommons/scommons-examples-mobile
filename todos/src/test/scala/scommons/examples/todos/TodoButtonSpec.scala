package scommons.examples.todos

import scommons.react.test.TestSpec
import scommons.react.test.raw.ShallowInstance
import scommons.react.test.util.ShallowRendererUtils
import scommons.reactnative._

import scala.scalajs.js

class TodoButtonSpec extends TestSpec with ShallowRendererUtils {

  it should "call onPress when onPress" in {
    //given
    val onPress = mockFunction[Unit]
    val props = TodoButtonProps(onPress, complete = false, name = "Complete")
    val comp = shallowRender(<(TodoButton())(^.wrapped := props)())
    val button = findComponents(comp, NativeTouchableHighlight).head
    
    //then
    onPress.expects()
    
    //when
    button.props.onPress()
  }
  
  it should "render simple button" in {
    //given
    val onPress = mockFunction[Unit]
    val props = TodoButtonProps(onPress, complete = false, name = "Todo")
    val component = <(TodoButton())(^.wrapped := props)()
    
    //when
    val result = shallowRender(component)
    
    //then
    assertTodoButton(result, props, List(
      TodoButton.styles.text
    ))
  }
  
  it should "render Complete button" in {
    //given
    val onPress = mockFunction[Unit]
    val props = TodoButtonProps(onPress, complete = true, name = "Complete")
    val component = <(TodoButton())(^.wrapped := props)()
    
    //when
    val result = shallowRender(component)
    
    //then
    assertTodoButton(result, props, List(
      TodoButton.styles.text,
      TodoButton.styles.complete
    ))
  }
  
  it should "render Delete button" in {
    //given
    val onPress = mockFunction[Unit]
    val props = TodoButtonProps(onPress, complete = false, name = "Delete")
    val component = <(TodoButton())(^.wrapped := props)()
    
    //when
    val result = shallowRender(component)
    
    //then
    assertTodoButton(result, props, List(
      TodoButton.styles.text,
      TodoButton.styles.deleteButton
    ))
  }
  
  private def assertTodoButton(result: ShallowInstance, props: TodoButtonProps, style: List[Style]): Unit = {
    assertNativeComponent(result,
      <.TouchableHighlight(
        ^.rnStyle := TodoButton.styles.button,
        ^.underlayColor := "#efefef"
      )(
        <.Text(^.rnStyle := js.Array(style: _*))(
          props.name
        )
      )
    )
  }
}
