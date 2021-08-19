package scommons.examples.todos

import scommons.react.test._
import scommons.reactnative._

class HeadingSpec extends TestSpec with TestRendererUtils {

  it should "render component" in {
    //given
    val component = <(Heading())()()
    
    //when
    val result = testRender(component)
    
    //then
    assertNativeComponent(result,
      <.View(^.rnStyle := Heading.styles.header)(
        <.Text(^.rnStyle := Heading.styles.headerText)(
          "todos"
        )
      )
    )
  }
}
