package scommons.examples.starwars.people

import scommons.examples.starwars.api.people._
import scommons.examples.starwars.api.planet.PlanetData
import scommons.examples.starwars.people.PeopleActions._
import scommons.react.redux._
import scommons.react.redux.task.{FutureTask, TaskAction}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Success

trait PeopleActions {

  protected def client: PeopleApi

  def peopleListFetch(dispatch: Dispatch): PeopleListFetchAction = {
    val future = client.getPeople.andThen {
      case Success(PeopleResp(dataList)) =>
        dispatch(PeopleListFetchedAction(dataList))
    }

    PeopleListFetchAction(FutureTask("Fetching People", future))
  }
  
  def homeWorldFetch(url: String): HomeWorldFetchAction = {
    val future = client.getHomeWorld(url)

    HomeWorldFetchAction(FutureTask("Fetching HomeWorld", future))
  }
}

object PeopleActions {

  case class PeopleListFetchAction(task: FutureTask[PeopleResp]) extends TaskAction
  case class PeopleListFetchedAction(dataList: List[PeopleData]) extends Action
  
  case class HomeWorldFetchAction(task: FutureTask[PlanetData]) extends TaskAction
}
