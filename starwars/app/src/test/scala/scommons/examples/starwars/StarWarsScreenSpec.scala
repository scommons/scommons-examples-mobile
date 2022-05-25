package scommons.examples.starwars

import scommons.examples.starwars.StarWarsScreen._
import scommons.react._
import scommons.react.test._
import scommons.reactnative.FlatList._
import scommons.reactnative._

import scala.scalajs.js
import scala.scalajs.js.Dynamic.literal

class StarWarsScreenSpec extends TestSpec with TestRendererUtils {

  StarWarsScreen.containerComp = mockUiComponent("Container")

  it should "call navigate when item onPress" in {
    //given
    val navigate = mockFunction[String, Unit]
    val props = StarWarsScreenProps(navigate)
    val comp = testRender(<(StarWarsScreen())(^.wrapped := props)())
    val flatList = inside(findComponents(comp, <.FlatList.reactClass)) {
      case List(flatList) => flatList
    }
    val data = dataList.head
    val itemMock = literal(
      item = data.asInstanceOf[js.Any],
      index = 0
    )
    val item = renderItem(flatList, itemMock)
    
    //then
    navigate.expects(*).onCall { value: String =>
      value shouldBe data.title
      ()
    }
    
    //when
    item.props.onPress()
  }

  it should "return data.title from keyExtractor" in {
    //given
    val props = StarWarsScreenProps(_ => ())
    val comp = testRender(<(StarWarsScreen())(^.wrapped := props)())
    val flatList = inside(findComponents(comp, <.FlatList.reactClass)) {
      case List(flatList) => flatList
    }
    val data = dataList.head
    
    //when
    val result = flatList.props.keyExtractor(data.asInstanceOf[js.Any])
    
    //then
    result shouldBe data.title
  }

  it should "render top item" in {
    //given
    val props = StarWarsScreenProps(_ => ())
    val comp = testRender(<(StarWarsScreen())(^.wrapped := props)())
    val flatList = inside(findComponents(comp, <.FlatList.reactClass)) {
      case List(flatList) => flatList
    }
    val data = dataList.head
    val itemMock = literal(
      item = data.asInstanceOf[js.Any],
      index = 0
    )

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
    val props = StarWarsScreenProps(_ => ())
    val comp = testRender(<(StarWarsScreen())(^.wrapped := props)())
    val flatList = inside(findComponents(comp, <.FlatList.reactClass)) {
      case List(flatList) => flatList
    }
    val data = dataList.head
    val itemMock = literal(
      item = data.asInstanceOf[js.Any],
      index = 1
    )

    //when
    val result = renderItem(flatList, itemMock)
    
    //then
    assertNativeComponent(result,
      <.TouchableHighlight(^.rnStyle := styles.item)(
        <.Text(^.rnStyle := styles.text)(data.title)
      )
    )
  }

  it should "render header component" in {
    //given
    val headerTitleComp = options.headerTitle.get.apply(null)
    val wrapper = new FunctionComponent[Unit] {
      protected def render(props: Props): ReactElement = {
        headerTitleComp
      }
    }

    //when
    val result = testRender(<(wrapper()).empty)

    //then
    assertNativeComponent(result,
      <.Text(^.rnStyle := styles.headerTitle)(
        "Star Wars"
      )
    )
  }

  it should "render main component" in {
    //given
    val props = StarWarsScreenProps(_ => ())
    val component = <(StarWarsScreen())(^.wrapped := props)()

    //when
    val result = testRender(component)

    //then
    assertNativeComponent(result,
      <(containerComp())()(
        <.FlatList(
          ^.flatListData := js.Array(dataList: _*)
        )()
      )
    )
  }
  
  private def renderItem(flatList: TestInstance, itemMock: js.Dynamic): TestInstance = {
    val wrapper = new FunctionComponent[Unit] {
      protected def render(compProps: Props): ReactElement = {
        val result = flatList.props.renderItem(itemMock.asInstanceOf[FlatListData[DataItem]])
        result.asInstanceOf[ReactElement]
      }
    }

    testRender(<(wrapper())()())
  }
}
