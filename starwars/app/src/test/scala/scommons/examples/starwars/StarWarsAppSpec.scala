package scommons.examples.starwars

import scommons.examples.starwars.StarWarsApp._
import scommons.react._
import scommons.react.navigation._
import scommons.react.navigation.stack._
import scommons.react.test.TestSpec
import scommons.react.test.util.ShallowRendererUtils
import scommons.reactnative._

class StarWarsAppSpec extends TestSpec with ShallowRendererUtils {

  it should "render header component" in {
    //given
    val headerTitleComp = screenOptions.headerTitle.get.apply(null)
    val wrapper = new FunctionComponent[Unit] {
      protected def render(props: Props): ReactElement = {
        headerTitleComp
      }
    }

    //when
    val result = shallowRender(<(wrapper()).empty)
    
    //then
    assertNativeComponent(result,
      <.Text(^.rnStyle := styles.headerTitle)(
        "Star Wars"
      )
    )
  }
  
  it should "render app component" in {
    //given
    val component = <(StarWarsApp())()()
    
    //when
    val result = shallowRender(component)
    
    //then
    assertNativeComponent(result,
      <.NavigationContainer()(
        <(Stack.Navigator)(^.initialRouteName := "StarWars", ^.screenOptions := screenOptions)(
          <(Stack.Screen)(^.name := "StarWars", ^.component := StarWarsScreen())()
        )
      )
    )
  }
}
