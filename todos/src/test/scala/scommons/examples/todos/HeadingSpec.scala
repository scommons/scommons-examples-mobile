package scommons.examples.todos

import scommons.react.test.TestSpec
import scommons.react.test.util.TestRendererUtils
import scommons.reactnative._

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
