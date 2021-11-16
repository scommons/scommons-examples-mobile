package scommons.examples.starwars.people

import scommons.examples.starwars.StarWarsStateDef
import scommons.react._
import scommons.react.navigation._
import scommons.react.redux.Dispatch
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
