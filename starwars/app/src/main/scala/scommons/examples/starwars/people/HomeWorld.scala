package scommons.examples.starwars.people

import scommons.examples.starwars.api.planet.PlanetData
import scommons.react._
import scommons.reactnative._

import scala.scalajs.js

case class HomeWorldProps(data: PlanetData,
                          closeModal: () => Unit)

object HomeWorld extends FunctionComponent[HomeWorldProps] {

  protected def render(compProps: Props): ReactElement = {
    val props = compProps.wrapped
    val data = props.data

    <.View(^.rnStyle := styles.container)(
      <.View(^.rnStyle := styles.infoContainer)(
        textContainer("Name", data.name),
        textContainer("Population", data.population),
        textContainer("Climate", data.climate),
        textContainer("Gravity", data.gravity),
        textContainer("Terrain", data.terrain),
        textContainer("Diameter", data.diameter),

        <.TouchableHighlight(^.onPress := props.closeModal)(
          <.Text(^.rnStyle := styles.closeButton)("Close Modal")
        )
      )
    )
  }

  private def textContainer(label: String, info: String): ReactElement = {
    <.Text(^.rnStyle := styles.text)(s"$label: $info")
  }

  private[people] lazy val styles = StyleSheet.create(new Styles)
  private[people] class Styles extends js.Object {
    import Style._

    val container: Style = new ViewStyle {
      override val flex = 1
      override val backgroundColor = "#000000"
      override val paddingTop = 20
    }
    val infoContainer: Style = new ViewStyle {
      override val padding = 20
    }
    val text: Style = new ViewStyle {
      override val color = "#ffe81f"
    }
    val closeButton: Style = new TextStyle {
      override val paddingTop = 20
      override val color = Color.white
      override val fontSize = 14
    }
  }
}
