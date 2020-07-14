package scommons.examples.starwars.people

import io.github.shogowada.scalajs.reactjs.redux.Redux.Dispatch
import org.scalatest.{Assertion, Succeeded}
import scommons.examples.starwars.Container
import scommons.examples.starwars.api.people._
import scommons.examples.starwars.api.planet.PlanetData
import scommons.examples.starwars.people.PeopleActions._
import scommons.examples.starwars.people.PeopleScreen._
import scommons.examples.starwars.people.PeopleScreenSpec.FlatListDataMock
import scommons.nodejs.test.AsyncTestSpec
import scommons.react._
import scommons.react.redux.task.FutureTask
import scommons.react.test._
import scommons.reactnative.FlatList._
import scommons.reactnative.Modal._
import scommons.reactnative._
import scommons.reactnative.raw.{FlatList, Modal, Picker, TouchableHighlight}

import scala.concurrent.Future
import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportAll

class PeopleScreenSpec extends AsyncTestSpec
  with BaseTestSpec
  with ShallowRendererUtils
  with TestRendererUtils {

  it should "toggle Picker when onPress in filter" in {
    //given
    val props = getPeopleScreenProps()
    val renderer = createRenderer()
    renderer.render(<(PeopleScreen())(^.wrapped := props)())
    assertPeopleScreen(renderer.getRenderOutput(), props)
    val List(filter) = findComponents(renderer.getRenderOutput(), TouchableHighlight)

    //when & then
    filter.props.onPress()
    assertPeopleScreen(renderer.getRenderOutput(), props, pickerVisible = true)

    //when & then
    filter.props.onPress()
    assertPeopleScreen(renderer.getRenderOutput(), props)
  }

  it should "filter data when onValueChange in Picker" in {
    //given
    val props = getPeopleScreenProps()
    val renderer = createRenderer()
    renderer.render(<(PeopleScreen())(^.wrapped := props)())
    val List(filter) = findComponents(renderer.getRenderOutput(), TouchableHighlight)
    filter.props.onPress()
    assertPeopleScreen(renderer.getRenderOutput(), props, pickerVisible = true)
    val List(picker) = findComponents(renderer.getRenderOutput(), Picker)

    //when & then
    picker.props.onValueChange("male", 2)
    assertPeopleScreen(renderer.getRenderOutput(), props.copy(
      data = props.data.copy(dataList = props.data.dataList.filter(_.gender == "male"))
    ), gender = "male", pickerVisible = true)
    
    //when & then
    picker.props.onValueChange("female", 3)
    assertPeopleScreen(renderer.getRenderOutput(), props.copy(
      data = props.data.copy(dataList = props.data.dataList.filter(_.gender == "female"))
    ), gender = "female", pickerVisible = true)
    
    //when & then
    picker.props.onValueChange("n/a", 4)
    assertPeopleScreen(renderer.getRenderOutput(), props.copy(
      data = props.data.copy(dataList = Nil)
    ), gender = "n/a", pickerVisible = true)
  }

  it should "dispatch actions if dataList is empty when mount" in {
    //given
    val dispatch = mockFunction[Any, Any]
    val actions = mock[PeopleActions]
    val props = {
      val props = getPeopleScreenProps(dispatch, actions = actions)
      props.copy(data = props.data.copy(dataList = Nil))
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
    val data = props.data.dataList.head
    
    //when
    val result = flatList.props.keyExtractor(data.asInstanceOf[js.Any])
    
    //then
    result shouldBe data.name
  }

  it should "dispatch action and show modal when press View Homeworld" in {
    //given
    val dispatch = mockFunction[Any, Any]
    val actions = mock[PeopleActions]
    val props = getPeopleScreenProps(dispatch, actions)
    val data = props.data.dataList.head
    val renderer = createRenderer()
    renderer.render(<(PeopleScreen())(^.wrapped := props)())
    findComponents(renderer.getRenderOutput(), Modal) shouldBe Nil
    
    val itemMock = mock[FlatListDataMock]
    (itemMock.item _).expects().returning(data)
    
    val List(flatList) = findComponents(renderer.getRenderOutput(), FlatList)
    val itemComp = renderItem(flatList, itemMock)
    val List(btn) = findComponents(itemComp, TouchableHighlight)

    val planetData = mock[PlanetData]
    val homeWorldFetchAction = HomeWorldFetchAction(
      FutureTask("Fetching HomeWorld", Future.successful(planetData))
    )
    (actions.homeWorldFetch _).expects(data.homeworld).returning(homeWorldFetchAction)

    //then
    dispatch.expects(homeWorldFetchAction)
    
    //when
    btn.props.onPress()
    
    homeWorldFetchAction.task.future.map { _ =>
      //then
      val List(modal) = findComponents(renderer.getRenderOutput(), Modal)
      
      assertNativeComponent(modal, <.Modal(^.animationType := AnimationType.slide)(), {
        children: List[ShallowInstance] =>
          val List(homeworld) = children
          assertComponent(homeworld, HomeWorld) { case HomeWorldProps(planet, closeModal) =>
            planet shouldBe planetData

            //when
            closeModal()
              
            //then
            findComponents(renderer.getRenderOutput(), Modal) shouldBe Nil
          }
      })
    }
  }

  it should "render item" in {
    //given
    val props = getPeopleScreenProps()
    val comp = shallowRender(<(PeopleScreen())(^.wrapped := props)())
    val List(flatList) = findComponents(comp, FlatList)
    val itemMock = mock[FlatListDataMock]
    val data = props.data.dataList.head
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

  it should "render component with closed filter" in {
    //given
    val props = getPeopleScreenProps()
    val component = <(PeopleScreen())(^.wrapped := props)()

    //when
    val result = shallowRender(component)

    //then
    assertPeopleScreen(result, props)
  }
  
  private def getPeopleScreenProps(dispatch: Dispatch = mock[Dispatch],
                                   actions: PeopleActions = mock[PeopleActions],
                                   data: PeopleState = PeopleState(List(
                                     PeopleData(
                                       name = "Test Male",
                                       height = "180",
                                       birth_year = "1981",
                                       gender = "male",
                                       homeworld = "/some/homeworld/url"
                                     ),
                                     PeopleData(
                                       name = "Test Female",
                                       height = "190",
                                       birth_year = "1991",
                                       gender = "female",
                                       homeworld = "/some/homeworld/url2"
                                     )
                                   ))): PeopleScreenProps = {
    PeopleScreenProps(
      dispatch = dispatch,
      actions = actions,
      data = data
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
  
  private def assertPeopleScreen(result: ShallowInstance,
                                 props: PeopleScreenProps,
                                 gender: String = "all",
                                 pickerVisible: Boolean = false): Assertion = {
    assertNativeComponent(result,
      <(Container())()(
        <.TouchableHighlight(^.rnStyle := styles.pickerToggleContainer)(
          <.Text(^.rnStyle := styles.pickerToggle)(
            if (pickerVisible) "Close Filter"
            else "Open Filter"
          )
        ),

        <.FlatList(
          ^.flatListData := js.Array(props.data.dataList: _*)
        )(),

        if (pickerVisible) Some(
          <.View(^.rnStyle := styles.pickerContainer)(
            <.Picker(
              ^.rnStyle := styles.picker,
              ^.selectedValue := gender
            )(
              <.PickerItem(^.rnStyle := styles.pickerAll, ^.label := "All", ^.value := "all")(),
              <.PickerItem(^.label := "Males", ^.value := "male")(),
              <.PickerItem(^.label := "Females", ^.value := "female")(),
              <.PickerItem(^.label := "Other", ^.value := "n/a")()
            )
          )
        ) else None
      )
    )
  }
}

object PeopleScreenSpec {

  @JSExportAll
  trait FlatListDataMock {
    def item: PeopleData
  }
}
