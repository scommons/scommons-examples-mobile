package scommons.examples.starwars

import scommons.react._
import scommons.reactnative.FlatList._
import scommons.reactnative._

import scala.scalajs.js

object StarWarsScreen extends FunctionComponent[Unit] {

  case class DataItem(title: String)
  
  lazy val dataList = List(
    DataItem("People"),
    DataItem("Films"),
    DataItem("StarShips"),
    DataItem("Vehicles"),
    DataItem("Species"),
    DataItem("Planets")
  )

  protected def render(props: Props): ReactElement = {

    def renderItem(item: DataItem, index: Int): ReactElement = {
      val styleAttr =
        if (index == 0) ^.rnStyle := js.Array(styles.item, styles.topItem)
        else ^.rnStyle := styles.item
  
      <.TouchableHighlight(styleAttr, ^.onPress := { () =>
      })(
        <.Text(^.rnStyle := styles.text)(item.title)
      )
    }
    
    <(Container())()(
      <.FlatList(
        ^.flatListData := js.Array(dataList: _*),
        ^.keyExtractor := { item: DataItem =>
          item.title
        },
        ^.renderItem := { data: FlatListData[DataItem] =>
          renderItem(data.item, data.index)
        }
      )()
    )
  }

  private[starwars] lazy val styles = StyleSheet.create(new Styles)
  private[starwars] class Styles extends js.Object {
    import ViewStyle._
    
    val item: Style = new ViewStyle {
      override val padding = 20
      override val justifyContent = JustifyContent.center
      override val borderColor = "rgba(255,232,31, .2)"
      override val borderBottomWidth = 1
    }
    val topItem: Style = new ViewStyle {
      override val borderTopWidth = 1
    }
    val text: Style = new TextStyle {
      override val color = "#ffe81f"
      override val fontSize = 18
    }
  }
}
