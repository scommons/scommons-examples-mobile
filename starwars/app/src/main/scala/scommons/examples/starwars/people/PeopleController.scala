package scommons.examples.starwars.people

import io.github.shogowada.scalajs.reactjs.redux.Redux.Dispatch
import scommons.examples.starwars.StarWarsStateDef
import scommons.react._
import scommons.react.navigation._
import scommons.reactnative.app.BaseStateAndRouteController

class PeopleController(actions: PeopleActions)
  extends BaseStateAndRouteController[StarWarsStateDef, PeopleScreenProps] {

  lazy val uiComponent: UiComponent[PeopleScreenProps] = PeopleScreen

  def mapStateAndRouteToProps(dispatch: Dispatch,
                              state: StarWarsStateDef,
                              nav: Navigation): PeopleScreenProps = {
    PeopleScreenProps(
      dispatch = dispatch,
      actions = actions,
      data = state.peopleState
    )
  }
}
