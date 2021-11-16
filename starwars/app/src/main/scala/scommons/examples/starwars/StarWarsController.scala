package scommons.examples.starwars

import scommons.react._
import scommons.react.navigation._
import scommons.react.redux.Dispatch
import scommons.reactnative.app.BaseStateAndRouteController

object StarWarsController
  extends BaseStateAndRouteController[StarWarsStateDef, StarWarsScreenProps] {

  lazy val uiComponent: UiComponent[StarWarsScreenProps] = StarWarsScreen

  def mapStateAndRouteToProps(dispatch: Dispatch,
                              state: StarWarsStateDef,
                              nav: Navigation): StarWarsScreenProps = {
    StarWarsScreenProps(
      navigate = { screen =>
        nav.navigate(screen)
      }
    )
  }
}
