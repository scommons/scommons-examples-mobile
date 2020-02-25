package scommons.examples.starwars.people

import io.github.shogowada.scalajs.reactjs.redux.Action
import io.github.shogowada.scalajs.reactjs.redux.Redux.Dispatch
import scommons.examples.starwars.api.people._
import scommons.examples.starwars.people.PeopleActions._
import scommons.react.redux.task.{FutureTask, TaskAction}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Success

trait PeopleActions {

  protected def client: PeopleApi

  def peopleListFetch(dispatch: Dispatch): PeopleListFetchAction = {
    val future = client.getPeople.andThen {
      case Success(dataList) =>
        dispatch(PeopleListFetchedAction(dataList))
    }

    PeopleListFetchAction(FutureTask("Fetching People", future))
  }
}

object PeopleActions {

  case class PeopleListFetchAction(task: FutureTask[List[PeopleData]]) extends TaskAction
  case class PeopleListFetchedAction(dataList: List[PeopleData]) extends Action
}
