package scommons.examples.starwars

import scommons.examples.starwars.StarWarsRoot._
import scommons.examples.starwars.people.PeopleController
import scommons.react._
import scommons.react.navigation._
import scommons.react.navigation.stack._
import scommons.reactnative._

import scala.scalajs.js

class StarWarsRoot(actions: StarWarsActions) extends FunctionComponent[Unit] {

  private[starwars] lazy val Stack = createStackNavigator()
  
  private[starwars] lazy val peopleComp = new PeopleController(actions).apply()

  protected def render(props: Props): ReactElement = {
    <.NavigationContainer()(
      <(Stack.Navigator)(^.initialRouteName := "StarWars", ^.screenOptions := screenOptions)(
        <(Stack.Screen)(
          ^.name := "StarWars",
          ^.component := StarWarsController(),
          ^.options := StarWarsScreen.options
        )(),
        <(Stack.Screen)(^.name := "People", ^.component := peopleComp)(),
        <(Stack.Screen)(^.name := "Films", ^.component := emptyComp)(),
        <(Stack.Screen)(^.name := "StarShips", ^.component := emptyComp)(),
        <(Stack.Screen)(^.name := "Vehicles", ^.component := emptyComp)(),
        <(Stack.Screen)(^.name := "Species", ^.component := emptyComp)(),
        <(Stack.Screen)(^.name := "Planets", ^.component := emptyComp)()
      )
    )
  }
}

object StarWarsRoot {
  
  private[starwars] lazy val emptyComp: ReactClass = new FunctionComponent[Unit] {
    override protected def render(props: Props): ReactElement = {
      <(Container())()()
    }
  }.apply()

  private[starwars] lazy val screenOptions = new StackScreenOptions {

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
