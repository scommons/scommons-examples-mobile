package scommons.examples.starwars

import scommons.examples.starwars.StarWarsRoot._
import scommons.examples.starwars.people.PeopleScreen
import scommons.react.navigation._
import scommons.react.navigation.stack._
import scommons.react.test.TestSpec
import scommons.react.test.util.ShallowRendererUtils

class StarWarsRootSpec extends TestSpec with ShallowRendererUtils {

  it should "render app component" in {
    //given
    val component = <(StarWarsRoot())()()
    
    //when
    val result = shallowRender(component)
    
    //then
    assertNativeComponent(result,
      <.NavigationContainer()(
        <(Stack.Navigator)(^.initialRouteName := "StarWars", ^.screenOptions := screenOptions)(
          <(Stack.Screen)(
            ^.name := "StarWars",
            ^.component := StarWarsController(),
            ^.options := StarWarsScreen.options
          )(),
          <(Stack.Screen)(^.name := "People", ^.component := PeopleScreen())(),
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
