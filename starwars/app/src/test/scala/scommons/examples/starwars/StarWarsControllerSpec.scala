package scommons.examples.starwars

import scommons.react.navigation.Navigation
import scommons.react.test.TestSpec

class StarWarsControllerSpec extends TestSpec {

  it should "return component" in {
    //given
    val controller = StarWarsController
    
    //when & then
    controller.uiComponent shouldBe StarWarsScreen
  }
  
  it should "map route to props" in {
    //given
    val controller = StarWarsController
    val nav = mock[Navigation]
    val routeName = "Styles"
    
    (nav.navigate(_: String)).expects(routeName)

    //when
    val result = controller.mapRouteToProps(nav)
    
    //then
    inside(result) {
      case StarWarsScreenProps(navigate) =>
        navigate(routeName)
    }
  }
}
