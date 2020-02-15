package scommons.examples.starwars

import scommons.examples.starwars.StarWarsScreen._
import scommons.examples.starwars.StarWarsScreenSpec.FlatListDataMock
import scommons.react._
import scommons.react.test.TestSpec
import scommons.react.test.raw.ShallowInstance
import scommons.react.test.util.ShallowRendererUtils
import scommons.reactnative.FlatList._
import scommons.reactnative._
import scommons.reactnative.raw.FlatList

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportAll

class StarWarsScreenSpec extends TestSpec with ShallowRendererUtils {

  it should "return data.title from keyExtractor" in {
    //given
    val comp = shallowRender(<(StarWarsScreen())()())
    val List(flatList) = findComponents(comp, FlatList)
    val data = dataList.head
    
    //when
    val result = flatList.props.keyExtractor(data.asInstanceOf[js.Any])
    
    //then
    result shouldBe data.title
  }

  it should "render top item" in {
    //given
    val comp = shallowRender(<(StarWarsScreen())()())
    val List(flatList) = findComponents(comp, FlatList)
    val itemMock = mock[FlatListDataMock]
    val data = dataList.head
    (itemMock.item _).expects().returning(data)
    (itemMock.index _).expects().returning(0)

    //when
    val result = renderItem(flatList, itemMock)
    
    //then
    assertNativeComponent(result,
      <.TouchableHighlight(^.rnStyle := js.Array(styles.item, styles.topItem))(
        <.Text(^.rnStyle := styles.text)(data.title)
      )
    )
  }

  it should "render non-top item" in {
    //given
    val comp = shallowRender(<(StarWarsScreen())()())
    val List(flatList) = findComponents(comp, FlatList)
    val itemMock = mock[FlatListDataMock]
    val data = dataList.head
    (itemMock.item _).expects().returning(data)
    (itemMock.index _).expects().returning(1)

    //when
    val result = renderItem(flatList, itemMock)
    
    //then
    assertNativeComponent(result,
      <.TouchableHighlight(^.rnStyle := styles.item)(
        <.Text(^.rnStyle := styles.text)(data.title)
      )
    )
  }

  it should "render main component" in {
    //given
    val component = <(StarWarsScreen())()()

    //when
    val result = shallowRender(component)

    //then
    assertNativeComponent(result,
      <(Container())()(
        <.FlatList(
          ^.flatListData := js.Array(dataList: _*)
        )()
      )
    )
  }
  
  private def renderItem(flatList: ShallowInstance, itemMock: FlatListDataMock): ShallowInstance = {
    val wrapper = new FunctionComponent[Unit] {
      protected def render(compProps: Props): ReactElement = {
        val result = flatList.props.renderItem(itemMock.asInstanceOf[FlatListData[DataItem]])
        result.asInstanceOf[ReactElement]
      }
    }

    shallowRender(<(wrapper())()())
  }
}

object StarWarsScreenSpec {

  @JSExportAll
  trait FlatListDataMock {
    def item: DataItem
    def index: Int
  }
}
