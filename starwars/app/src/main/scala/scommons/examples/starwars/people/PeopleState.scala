package scommons.examples.starwars.people

import scommons.examples.starwars.api.people.PeopleData
import scommons.examples.starwars.people.PeopleActions._

case class PeopleState(dataList: List[PeopleData] = Nil)

object PeopleStateReducer {

  def apply(state: Option[PeopleState], action: Any): PeopleState = {
    reduce(state.getOrElse(PeopleState()), action)
  }

  private def reduce(state: PeopleState, action: Any): PeopleState = action match {
    case PeopleListFetchedAction(dataList) => state.copy(
      dataList = dataList
    )
    case _ => state
  }
}
