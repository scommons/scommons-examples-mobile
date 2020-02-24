package scommons.examples.starwars

import scommons.react._
import scommons.reactnative.FlatList._
import scommons.reactnative._

import scala.scalajs.js

object PeopleScreen extends FunctionComponent[Unit] {

  case class DataItem(name: String,
                      height: String,
                      birth_year: String,
                      gender: String)

  lazy val dataList = List(
    DataItem("Viktor", "180", "1981", "male")
  )

  protected def render(props: Props): ReactElement = {
    
    def renderItem(item: DataItem): ReactElement = {
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
        ^.flatListData := js.Array(dataList: _*),
        ^.keyExtractor := { item: DataItem =>
          item.name
        },
        ^.renderItem := { data: FlatListData[DataItem] =>
          renderItem(data.item)
        }
      )()
    )
  }

  private[starwars] lazy val styles = StyleSheet.create(new Styles)
  private[starwars] class Styles extends js.Object {

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
