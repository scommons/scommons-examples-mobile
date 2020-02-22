package scommons.examples.starwars

import scommons.react._
import scommons.react.navigation._
import scommons.react.navigation.stack._

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel(name = "StarWarsApp")
object StarWarsApp extends FunctionComponent[Unit] {

  @JSExport("apply")
  override def apply(): ReactClass = super.apply()

  private[starwars] lazy val Stack = createStackNavigator()
  
  protected def render(props: Props): ReactElement = {
    <.NavigationContainer()(
      <(Stack.Navigator)(^.initialRouteName := "StarWars")(
        <(Stack.Screen)(^.name := "StarWars", ^.component := StarWarsScreen(), ^.options := StarWarsScreen.options)()
      )
    )
  }
}
