package scommons.examples.starwars

import scommons.examples.starwars.Container._
import scommons.react.test._
import scommons.reactnative._

class ContainerSpec extends TestSpec with TestRendererUtils {

  it should "render component" in {
    //given
    val child = <.Text()("Some test child")
    
    //when
    val result = testRender(<(Container())()(
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
