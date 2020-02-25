package scommons.examples.starwars.people

import scommons.examples.starwars.Container
import scommons.examples.starwars.api.people.PeopleData
import scommons.examples.starwars.people.PeopleScreen._
import scommons.examples.starwars.people.PeopleScreenSpec.FlatListDataMock
import scommons.react._
import scommons.react.test.TestSpec
import scommons.react.test.raw.ShallowInstance
import scommons.react.test.util.ShallowRendererUtils
import scommons.reactnative.FlatList._
import scommons.reactnative._
import scommons.reactnative.raw.FlatList

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportAll

class PeopleScreenSpec extends TestSpec with ShallowRendererUtils {

  it should "return data.name from keyExtractor" in {
    //given
    val comp = shallowRender(<(PeopleScreen())()())
    val List(flatList) = findComponents(comp, FlatList)
    val data = dataList.head
    
    //when
    val result = flatList.props.keyExtractor(data.asInstanceOf[js.Any])
    
    //then
    result shouldBe data.name
  }

  it should "render item" in {
    //given
    val comp = shallowRender(<(PeopleScreen())()())
    val List(flatList) = findComponents(comp, FlatList)
    val itemMock = mock[FlatListDataMock]
    val data = dataList.head
    (itemMock.item _).expects().returning(data)

    //when
    val result = renderItem(flatList, itemMock)
    
    //then
    assertNativeComponent(result,
      <.View(^.rnStyle := styles.itemContainer)(
        <.Text(^.rnStyle := styles.name)(data.name),
        <.Text(^.rnStyle := styles.info)(s"Height: ${data.height}"),
        <.Text(^.rnStyle := styles.info)(s"Birth Year: ${data.birth_year}"),
        <.Text(^.rnStyle := styles.info)(s"Gender: ${data.gender}"),
        <.TouchableHighlight()(
          <.Text(^.rnStyle := styles.info)("View Homeworld")
        )
      )
    )
  }

  it should "render main component" in {
    //given
    val component = <(PeopleScreen())()()

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
        val result = flatList.props.renderItem(itemMock.asInstanceOf[FlatListData[PeopleData]])
        result.asInstanceOf[ReactElement]
      }
    }

    shallowRender(<(wrapper())()())
  }
}

object PeopleScreenSpec {

  @JSExportAll
  trait FlatListDataMock {
    def item: PeopleData
  }
}
