package scommons.examples.starwars.people

import io.github.shogowada.scalajs.reactjs.redux.Redux.Dispatch
import scommons.examples.starwars.Container
import scommons.examples.starwars.api.people.PeopleData
import scommons.react._
import scommons.react.hooks._
import scommons.reactnative.FlatList._
import scommons.reactnative._

import scala.scalajs.js

case class PeopleScreenProps(dispatch: Dispatch,
                             actions: PeopleActions,
                             state: PeopleState)

object PeopleScreen extends FunctionComponent[PeopleScreenProps] {

  protected def render(compProps: Props): ReactElement = {
    val props = compProps.wrapped
    
    useEffect({ () =>
      if (props.state.dataList.isEmpty) {
        props.dispatch(props.actions.peopleListFetch(props.dispatch))
      }
      ()
    }, Nil)
    
    def renderItem(item: PeopleData): ReactElement = {
      <.View(^.rnStyle := styles.itemContainer)(
        <.Text(^.rnStyle := styles.name)(item.name),
        <.Text(^.rnStyle := styles.info)(s"Height: ${item.height}"),
        <.Text(^.rnStyle := styles.info)(s"Birth Year: ${item.birth_year}"),
        <.Text(^.rnStyle := styles.info)(s"Gender: ${item.gender}"),
        <.TouchableHighlight(^.onPress := { () => })(
          <.Text(^.rnStyle := styles.info)("View Homeworld")
        )
      )
    }

    <(Container())()(
      <.FlatList(
        ^.flatListData := js.Array(props.state.dataList: _*),
        ^.keyExtractor := { item: PeopleData =>
          item.name
        },
        ^.renderItem := { data: FlatListData[PeopleData] =>
          renderItem(data.item)
        }
      )()
    )
  }

  private[people] lazy val styles = StyleSheet.create(new Styles)
  private[people] class Styles extends js.Object {

    val itemContainer: Style = new ViewStyle {
      override val padding = 15
      override val borderBottomWidth = 1
      override val borderBottomColor = "#ffe81f"
    }
    val name: Style = new TextStyle {
      override val color = "#ffe81f"
      override val fontSize = 18
    }
    val info: Style = new TextStyle {
      override val color = "#ffe81f"
      override val fontSize = 14
      override val marginTop = 5
    }
  }
}
