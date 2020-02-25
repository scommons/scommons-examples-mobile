package scommons.examples.starwars

import scommons.api.ApiResponse
import scommons.react._
import scommons.react.redux.task.TaskManagerUiProps

import scala.util.{Success, Try}

object AppTaskManagerUi extends FunctionComponent[TaskManagerUiProps] {

  var errorHandler: PartialFunction[Try[_], (Option[String], Option[String])] = {
    case Success(result) => result match {
      case res: ApiResponse if res.status.nonSuccessful =>
        (Some(res.status.error.getOrElse("Non-successful response")), res.status.details)
      case _ =>
        (None, None)
    }
  }

  protected def render(compProps: Props): ReactElement = {
    <.>()()
  }
}
