package scommons.examples.starwars.people

import scommons.examples.starwars.api.planet.PlanetData
import scommons.examples.starwars.people.HomeWorld.styles
import scommons.react.test.TestSpec
import scommons.react.test.util.ShallowRendererUtils
import scommons.reactnative._

class HomeWorldSpec extends TestSpec with ShallowRendererUtils {

  it should "call closeModal when press Close Modal button" in {
    //given
    val closeModal = mockFunction[Unit]
    val props = getHomeWorldProps(closeModal = closeModal)
    val comp = shallowRender(<(HomeWorld())(^.wrapped := props)())
    val List(button) = findComponents(comp, raw.TouchableHighlight)
    
    //then
    closeModal.expects()
    
    //when
    button.props.onPress()
  }
  
  it should "render component" in {
    //given
    val props = getHomeWorldProps()

    //when
    val result = shallowRender(<(HomeWorld())(^.wrapped := props)())

    //then
    val data = props.data
    assertNativeComponent(result,
      <.View(^.rnStyle := styles.container)(
        <.View(^.rnStyle := styles.infoContainer)(
          <.Text(^.rnStyle := styles.text)(s"Name: ${data.name}"),
          <.Text(^.rnStyle := styles.text)(s"Population: ${data.population}"),
          <.Text(^.rnStyle := styles.text)(s"Climate: ${data.climate}"),
          <.Text(^.rnStyle := styles.text)(s"Gravity: ${data.gravity}"),
          <.Text(^.rnStyle := styles.text)(s"Terrain: ${data.terrain}"),
          <.Text(^.rnStyle := styles.text)(s"Diameter: ${data.diameter}"),

          <.TouchableHighlight()(
            <.Text(^.rnStyle := styles.closeButton)("Close Modal")
          )
        )
      )
    )
  }

  private def getHomeWorldProps(data: PlanetData = PlanetData(
                                  name = "Tatooine",
                                  population = "200000",
                                  climate = "arid",
                                  gravity = "1 standard",
                                  terrain = "desert",
                                  diameter = "10465"
                                ),
                                closeModal: () => Unit = () => ()): HomeWorldProps = {
    HomeWorldProps(
      data = data,
      closeModal = closeModal
    )
  }
}
