package scommons.examples.starwars

import scommons.react._
import scommons.react.navigation.stack._
import scommons.reactnative.FlatList._
import scommons.reactnative._

import scala.scalajs.js

case class StarWarsScreenProps(navigate: String => Unit)

object StarWarsScreen extends FunctionComponent[StarWarsScreenProps] {

  case class DataItem(title: String)
  
  lazy val dataList = List(
    DataItem("People"),
    DataItem("Films"),
    DataItem("StarShips"),
    DataItem("Vehicles"),
    DataItem("Species"),
    DataItem("Planets")
  )

  protected def render(compProps: Props): ReactElement = {
    val props = compProps.wrapped

    def renderItem(item: DataItem, index: Int): ReactElement = {
      val styleAttr =
        if (index == 0) ^.rnStyle := js.Array(styles.item, styles.topItem)
        else ^.rnStyle := styles.item
  
      <.TouchableHighlight(styleAttr, ^.onPress := { () =>
        props.navigate(item.title)
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

  lazy val options: StackScreenOptions = new StackScreenOptions {

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
    import ViewStyle._

    val header: Style = new ViewStyle {
      override val backgroundColor = Color.black
      override val height = 110
    }
    val headerTitle: Style = new TextStyle {
      override val fontSize = 34
      override val color = "rgb(255,232,31)"
    }
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
