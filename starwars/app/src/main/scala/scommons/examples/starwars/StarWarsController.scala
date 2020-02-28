package scommons.examples.starwars

import io.github.shogowada.scalajs.reactjs.redux.Redux.Dispatch
import scommons.react._
import scommons.react.navigation._
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
