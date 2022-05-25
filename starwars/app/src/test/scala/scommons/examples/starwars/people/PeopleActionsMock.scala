package scommons.examples.starwars.people

import scommons.examples.starwars.api.people.PeopleApi
import scommons.examples.starwars.people.PeopleActions._
import scommons.react.redux.Dispatch

//noinspection NotImplementedCode
class PeopleActionsMock(
  peopleListFetchMock: Dispatch => PeopleListFetchAction = _ => ???,
  homeWorldFetchMock: String => HomeWorldFetchAction = _ => ???
) extends PeopleActions {

  protected def client: PeopleApi = ???
  
  override def peopleListFetch(dispatch: Dispatch): PeopleListFetchAction =
    peopleListFetchMock(dispatch)
    
  override def homeWorldFetch(url: String): HomeWorldFetchAction =
    homeWorldFetchMock(url)
}
