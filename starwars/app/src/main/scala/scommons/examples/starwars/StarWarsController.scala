package scommons.examples.starwars

import scommons.react._
import scommons.react.navigation._

object StarWarsController extends BaseRouteController[StarWarsScreenProps] {

  lazy val uiComponent: UiComponent[StarWarsScreenProps] = StarWarsScreen

  def mapRouteToProps(nav: Navigation): StarWarsScreenProps = {
    StarWarsScreenProps(
      navigate = { screen =>
        nav.navigate(screen)
      }
    )
  }
}
