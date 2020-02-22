package scommons.examples.starwars

import scommons.examples.starwars.StarWarsApp._
import scommons.react.navigation._
import scommons.react.navigation.stack._
import scommons.react.test.TestSpec
import scommons.react.test.util.ShallowRendererUtils

class StarWarsAppSpec extends TestSpec with ShallowRendererUtils {

  it should "render app component" in {
    //given
    val component = <(StarWarsApp())()()
    
    //when
    val result = shallowRender(component)
    
    //then
    assertNativeComponent(result,
      <.NavigationContainer()(
        <(Stack.Navigator)(^.initialRouteName := "StarWars")(
          <(Stack.Screen)(^.name := "StarWars", ^.component := StarWarsScreen(), ^.options := StarWarsScreen.options)()
        )
      )
    )
  }
}
