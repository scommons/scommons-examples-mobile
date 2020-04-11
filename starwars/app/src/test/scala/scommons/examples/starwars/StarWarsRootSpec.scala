package scommons.examples.starwars

import scommons.examples.starwars.StarWarsRoot._
import scommons.react.navigation._
import scommons.react.test.TestSpec
import scommons.react.test.util.ShallowRendererUtils

class StarWarsRootSpec extends TestSpec with ShallowRendererUtils {

  it should "render app component" in {
    //given
    val actions = mock[StarWarsActions]
    val rootComp = new StarWarsRoot(actions)
    val component = <(rootComp())()()
    
    //when
    val result = shallowRender(component)
    
    //then
    import rootComp._
    
    assertNativeComponent(result,
      <.NavigationContainer()(
        <(Stack.Navigator)(^.initialRouteName := "StarWars", ^.screenOptions := screenOptions)(
          <(Stack.Screen)(
            ^.name := "StarWars",
            ^.component := StarWarsController(),
            ^.options := StarWarsScreen.options
          )(),
          <(Stack.Screen)(^.name := "People", ^.component := peopleComp)(),
          <(Stack.Screen)(^.name := "Films", ^.component := emptyComp)(),
          <(Stack.Screen)(^.name := "StarShips", ^.component := emptyComp)(),
          <(Stack.Screen)(^.name := "Vehicles", ^.component := emptyComp)(),
          <(Stack.Screen)(^.name := "Species", ^.component := emptyComp)(),
          <(Stack.Screen)(^.name := "Planets", ^.component := emptyComp)()
        )
      )
    )
  }
}
