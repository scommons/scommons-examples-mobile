package scommons.examples.starwars

import scommons.examples.starwars.people.{PeopleState, PeopleStateReducer}
import scommons.react.redux.task.{AbstractTask, TaskAction}

trait StarWarsStateDef {

  def currentTask: Option[AbstractTask]
  def peopleState: PeopleState
}

case class StarWarsState(currentTask: Option[AbstractTask],
                         peopleState: PeopleState
                        ) extends StarWarsStateDef

object StarWarsStateReducer {

  def reduce(state: Option[StarWarsState], action: Any): StarWarsState = StarWarsState(
    currentTask = currentTaskReducer(state.flatMap(_.currentTask), action),
    peopleState = PeopleStateReducer(state.map(_.peopleState), action)
  )

  private def currentTaskReducer(
    currentTask: Option[AbstractTask],
    action: Any): Option[AbstractTask] = action match {

    case a: TaskAction => Some(a.task)
    case _ => currentTask
  }
}
