package scommons.examples.starwars

import scommons.examples.starwars.Container._
import scommons.react.test.TestSpec
import scommons.react.test.util.ShallowRendererUtils
import scommons.reactnative._

class ContainerSpec extends TestSpec with ShallowRendererUtils {

  it should "render component" in {
    //given
    val child = <.Text()("Some test child")
    
    //when
    val result = shallowRender(<(Container())()(
      child
    ))
    
    //then
    assertNativeComponent(result,
      <.View(^.rnStyle := styles.container)(
        child
      )
    )
  }
}
