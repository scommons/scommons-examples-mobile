package scommons.examples.starwars

import scommons.react._
import scommons.react.navigation._
import scommons.react.navigation.stack._
import scommons.reactnative._

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel(name = "StarWarsApp")
object StarWarsApp extends FunctionComponent[Unit] {

  @JSExport("apply")
  override def apply(): ReactClass = super.apply()

  private[starwars] lazy val Stack = createStackNavigator()
  
  protected def render(props: Props): ReactElement = {
    <.NavigationContainer()(
      <(Stack.Navigator)(^.initialRouteName := "StarWars", ^.screenOptions := screenOptions)(
        <(Stack.Screen)(^.name := "StarWars", ^.component := StarWarsScreen())()
      )
    )
  }

  private[starwars] lazy val screenOptions: ScreenOptions = new ScreenOptions {
    
    override val headerTitle = { _: js.Dynamic =>
      headerTitleComp
    }: js.Function1[js.Dynamic, ReactElement]
    
    override val headerStyle = styles.header
  }
  
  private lazy val headerTitleComp: ReactElement = {
    <.Text(^.rnStyle := styles.headerTitle)(
      "Star Wars"
    )
  }

  private[starwars] lazy val styles = StyleSheet.create(new Styles)
  private[starwars] class Styles extends js.Object {
    import Style._

    val header: Style = new ViewStyle {
      override val backgroundColor = Color.black
      override val height = 110
    }
    val headerTitle: Style = new TextStyle {
      override val fontSize = 34
      override val color = "rgb(255,232,31)"
    }
  }
}
