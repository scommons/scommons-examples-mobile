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
  
  protected def render(props: Props): ReactElement = {
    <.Provider(^.store := store)(
      <.>()(
        <(StarWarsRoot()).empty,
        <(StarWarsTaskController()).empty
      )
    )
  }
}
