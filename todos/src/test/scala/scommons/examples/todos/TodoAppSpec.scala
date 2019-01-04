package scommons.examples.todos

import scommons.mobile.ui.ReactNativeView
import scommons.react.test.TestSpec
import scommons.react.test.raw.TestRenderer

class TodoAppSpec extends TestSpec {

  it should "render component" in {
    //given
    val component = <(TodoApp())()()
    
    //when
    val result = TestRenderer.create(component).root
    
    //then
    result.`type` shouldBe TodoApp()
    
    val container = result.children(0)
    container.`type` shouldBe ReactNativeView
    container.props.style shouldBe TodoApp.styles.container
  }
}
