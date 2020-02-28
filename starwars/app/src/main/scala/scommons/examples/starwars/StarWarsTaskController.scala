package scommons.examples.starwars

import io.github.shogowada.scalajs.reactjs.React.Props
import io.github.shogowada.scalajs.reactjs.redux.Redux.Dispatch
import scommons.react.UiComponent
import scommons.react.redux.BaseStateController
import scommons.react.redux.task.{TaskManager, TaskManagerProps}
import scommons.reactnative.Style
import scommons.reactnative.app.AppTaskManagerUi
import scommons.reactnative.ui.popup.LoadingPopupProps

object StarWarsTaskController
  extends BaseStateController[StarWarsStateDef, TaskManagerProps] {

  lazy val uiComponent: UiComponent[TaskManagerProps] = {
    TaskManager.uiComponent = new AppTaskManagerUi(
      loadingProps = LoadingPopupProps(color = Style.Color.yellow)
    )
    TaskManager.errorHandler = AppTaskManagerUi.errorHandler
    TaskManager
  }

  def mapStateToProps(dispatch: Dispatch, state: StarWarsStateDef, props: Props[Unit]): TaskManagerProps = {
    TaskManagerProps(state.currentTask)
  }
}
