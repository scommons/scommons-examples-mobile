package scommons.examples.starwars.people

import io.github.shogowada.scalajs.reactjs.redux.Redux.Dispatch
import org.scalatest.Succeeded
import scommons.examples.starwars.Container
import scommons.examples.starwars.api.people._
import scommons.examples.starwars.people.PeopleActions._
import scommons.examples.starwars.people.PeopleScreen._
import scommons.examples.starwars.people.PeopleScreenSpec.FlatListDataMock
import scommons.react._
import scommons.react.redux.task.FutureTask
import scommons.react.test.TestSpec
import scommons.react.test.raw.ShallowInstance
import scommons.react.test.util.{ShallowRendererUtils, TestRendererUtils}
import scommons.reactnative.FlatList._
import scommons.reactnative._
import scommons.reactnative.raw.FlatList

import scala.concurrent.Future
import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportAll

class PeopleScreenSpec extends TestSpec
  with ShallowRendererUtils
  with TestRendererUtils {

  it should "dispatch actions if dataList is empty when mount" in {
    //given
    val dispatch = mockFunction[Any, Any]
    val actions = mock[PeopleActions]
    val props = {
      val props = getPeopleScreenProps(dispatch, actions = actions)
      props.copy(state = props.state.copy(dataList = Nil))
    }
    val listFetchAction = PeopleListFetchAction(
      FutureTask("Fetching People", Future.successful(PeopleResp(Nil)))
    )
    (actions.peopleListFetch _).expects(dispatch).returning(listFetchAction)

    //then
    dispatch.expects(listFetchAction)

    //when
    val renderer = createTestRenderer(<(PeopleScreen())(^.wrapped := props)())

    //cleanup
    renderer.unmount()
    Succeeded
  }

  it should "not dispatch actions if dataList is non-empty when mount" in {
    //given
    val dispatch = mockFunction[Any, Any]
    val props = getPeopleScreenProps(dispatch)

    //then
    dispatch.expects(*).never()

    //when
    val renderer = createTestRenderer(<(PeopleScreen())(^.wrapped := props)())

    //cleanup
    renderer.unmount()
    Succeeded
  }

  it should "return data.name from keyExtractor" in {
    //given
    val props = getPeopleScreenProps()
    val comp = shallowRender(<(PeopleScreen())(^.wrapped := props)())
    val List(flatList) = findComponents(comp, FlatList)
    val data = props.state.dataList.head
    
    //when
    val result = flatList.props.keyExtractor(data.asInstanceOf[js.Any])
    
    //then
    result shouldBe data.name
  }

  it should "render item" in {
    //given
    val props = getPeopleScreenProps()
    val comp = shallowRender(<(PeopleScreen())(^.wrapped := props)())
    val List(flatList) = findComponents(comp, FlatList)
    val itemMock = mock[FlatListDataMock]
    val data = props.state.dataList.head
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
    val props = getPeopleScreenProps()
    val component = <(PeopleScreen())(^.wrapped := props)()

    //when
    val result = shallowRender(component)

    //then
    assertNativeComponent(result,
      <(Container())()(
        <.FlatList(
          ^.flatListData := js.Array(props.state.dataList: _*)
        )()
      )
    )
  }
  
  private def getPeopleScreenProps(dispatch: Dispatch = mock[Dispatch],
                                   actions: PeopleActions = mock[PeopleActions],
                                   state: PeopleState = PeopleState(List(
                                     PeopleData(
                                       name = "Test",
                                       height = "180",
                                       birth_year = "1981",
                                       gender = "male"
                                     )
                                   ))): PeopleScreenProps = {
    PeopleScreenProps(
      dispatch = dispatch,
      actions = actions,
      state = state
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
