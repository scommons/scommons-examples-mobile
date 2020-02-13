package scommons.examples.starwars

import scommons.react._
import scommons.react.navigation._
import scommons.react.navigation.stack._

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel(name = "StarWarsApp")
object StarWarsApp extends FunctionComponent[Unit] {

  @JSExport("apply")
  override def apply(): ReactClass = super.apply()

  private lazy val Stack = createStackNavigator()
  
  protected def render(props: Props): ReactElement = {
    <.NavigationContainer()(
      <(Stack.Navigator)(^.initialRouteName := "Root")(
        <(Stack.Screen)(^.name := "Root", ^.component := Root())()
      )
    )
  }

  object Root extends FunctionComponent[Unit] {
    protected def render(props: Props): ReactElement = {
      <.>()()
    }
  }
}
