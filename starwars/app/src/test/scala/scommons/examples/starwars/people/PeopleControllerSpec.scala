package scommons.examples.starwars.people

import scommons.examples.starwars.StarWarsState
import scommons.react.navigation.Navigation
import scommons.react.redux.Dispatch
import scommons.react.test.TestSpec

class PeopleControllerSpec extends TestSpec {

  it should "return component" in {
    //given
    val actions = mock[PeopleActions]
    val controller = new PeopleController(actions)
    
    //when & then
    controller.uiComponent shouldBe PeopleScreen
  }
  
  it should "map state to props" in {
    //given
    val dispatch = mock[Dispatch]
    val actions = mock[PeopleActions]
    val controller = new PeopleController(actions)
    val peopleState = mock[PeopleState]
    val state = StarWarsState(None, peopleState)
    val nav = mock[Navigation]

    //when
    val result = controller.mapStateAndRouteToProps(dispatch, state, nav)
    
    //then
    inside(result) {
      case PeopleScreenProps(resDispatch, resActions, resState) =>
        resDispatch shouldBe dispatch
        resActions shouldBe actions
        resState shouldBe peopleState
    }
  }
}
