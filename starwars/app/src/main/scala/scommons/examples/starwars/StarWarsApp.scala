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
        <(Stack.Screen)(
          ^.name := "StarWars",
          ^.component := StarWarsController(),
          ^.options := StarWarsScreen.options
        )(),
        <(Stack.Screen)(^.name := "People", ^.component := emptyComp)(),
        <(Stack.Screen)(^.name := "Films", ^.component := emptyComp)(),
        <(Stack.Screen)(^.name := "StarShips", ^.component := emptyComp)(),
        <(Stack.Screen)(^.name := "Vehicles", ^.component := emptyComp)(),
        <(Stack.Screen)(^.name := "Species", ^.component := emptyComp)(),
        <(Stack.Screen)(^.name := "Planets", ^.component := emptyComp)()
      )
    )
  }
  
  private[starwars] lazy val emptyComp: ReactClass = new FunctionComponent[Unit] {
    override protected def render(props: Props): ReactElement = {
      <(Container())()()
    }
  }.apply()

  private[starwars] lazy val screenOptions = new ScreenOptions {

    override val headerStyle = styles.header
    override val headerTintColor = "#ffe81f"
    val headerPressColorAndroid = "white"
    val headerBackTitleVisible = false
  }

  private[starwars] lazy val styles = StyleSheet.create(new Styles)
  private[starwars] class Styles extends js.Object {
    import Style._

    val header: Style = new ViewStyle {
      override val borderBottomWidth = 1
      override val borderBottomColor = "#ffe81f"
      override val backgroundColor = Color.black
    }
  }
}
