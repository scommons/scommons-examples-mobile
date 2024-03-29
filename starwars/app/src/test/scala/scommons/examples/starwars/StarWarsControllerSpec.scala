package scommons.examples.starwars

import scommons.react.navigation.{Navigation, raw}
import scommons.react.redux.Dispatch
import scommons.react.test.TestSpec

import scala.scalajs.js

class StarWarsControllerSpec extends TestSpec {

  it should "return component" in {
    //given
    val controller = StarWarsController
    
    //when & then
    controller.uiComponent shouldBe StarWarsScreen
  }
  
  it should "map route to props" in {
    //given
    val dispatch = mock[Dispatch]
    val controller = StarWarsController
    val state = mock[StarWarsStateDef]
    val navigateMock = mockFunction[String, Unit]
    val navigation = js.Dynamic.literal(navigate = navigateMock)
    val nav = new Navigation(navigation.asInstanceOf[raw.Navigation], null)
    val routeName = "Styles"
    
    navigateMock.expects(*).onCall { value: String =>
      value shouldBe routeName
      ()
    }

    //when
    val result = controller.mapStateAndRouteToProps(dispatch, state, nav)
    
    //then
    inside(result) {
      case StarWarsScreenProps(navigate) =>
        navigate(routeName)
    }
  }
}
