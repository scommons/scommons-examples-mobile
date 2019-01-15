package scommons.examples.todos

import scommons.react.test.TestSpec
import scommons.reactnative._
import scommons.reactnative.test.util.TestRendererUtils

class HeadingSpec extends TestSpec with TestRendererUtils {

  it should "render component" in {
    //given
    val component = <(Heading())()()
    
    //when
    val result = render(component)
    
    //then
    assertNativeComponent(result,
      <("View")(^.rnStyle := Heading.styles.header)(
        <("Text")(^.rnStyle := Heading.styles.headerText)(
          "todos"
        )
      )
    )
  }
}
