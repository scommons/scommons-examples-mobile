package scommons.examples.starwars

import io.github.shogowada.scalajs.reactjs.redux.ReactRedux._
import io.github.shogowada.scalajs.reactjs.redux.Redux
import scommons.react._

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel(name = "StarWarsApp")
object StarWarsApp extends FunctionComponent[Unit] {

  @JSExport("apply")
  override def apply(): ReactClass = super.apply()

  private val store = Redux.createStore(StarWarsStateReducer.reduce)
  
  private lazy val actions = StarWarsActions
  private lazy val rootComp = new StarWarsRoot(actions).apply()
  
  protected def render(props: Props): ReactElement = {
    <.Provider(^.store := store)(
      <.>()(
        <(rootComp).empty,
        <(StarWarsTaskController()).empty
      )
    )
  }
}
