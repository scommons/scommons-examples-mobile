package scommons.react.test

import io.github.shogowada.scalajs.reactjs.React
import scommons.examples.TestSpec
import scommons.mobile.ui.NativeText._
import scommons.mobile.ui.NativeView._
import scommons.mobile.ui.ReactNativeView

class TestRendererSpec extends TestSpec {

  it should "render component" in {
    //given
    val compClass = React.createClass[Unit, Unit] { _ =>
      <.View(^("testProp") := "test")(
        <.Text()("Test")
      )
    }
    
    //when
    val result = TestRenderer.create(<(compClass)()()).root
    
    //then
    result.`type` shouldBe compClass
    
    val container = result.children(0)
    container.`type` shouldBe ReactNativeView
    container.props.testProp shouldBe "test"
  }
}
