package scommons.examples.starwars.people

import org.scalatest.{Assertion, Succeeded}
import scommons.examples.starwars.api.people._
import scommons.examples.starwars.api.planet.PlanetData
import scommons.examples.starwars.people.PeopleActions._
import scommons.examples.starwars.people.PeopleScreen._
import scommons.nodejs.test.AsyncTestSpec
import scommons.react._
import scommons.react.redux.Dispatch
import scommons.react.redux.task.FutureTask
import scommons.react.test._
import scommons.reactnative.FlatList._
import scommons.reactnative.Modal._
import scommons.reactnative._
import scommons.reactnative.picker._

import scala.concurrent.Future
import scala.scalajs.js
import scala.scalajs.js.Dynamic.literal

class PeopleScreenSpec extends AsyncTestSpec
  with BaseTestSpec
  with TestRendererUtils {

  PeopleScreen.containerComp = mockUiComponent("Container")
  PeopleScreen.homeWorldComp = mockUiComponent("HomeWorld")

  //noinspection TypeAnnotation
  class Actions {
    val peopleListFetch = mockFunction[Dispatch, PeopleListFetchAction]
    val homeWorldFetch = mockFunction[String, HomeWorldFetchAction]

    val actions = new PeopleActionsMock(
      peopleListFetchMock = peopleListFetch,
      homeWorldFetchMock = homeWorldFetch
    )
  }

  it should "toggle Picker when onPress in filter" in {
    //given
    val props = getPeopleScreenProps()
    val renderer = createTestRenderer(<(PeopleScreen())(^.wrapped := props)())
    assertPeopleScreen(renderer.root.children(0), props)
    val filter = inside(findComponents(renderer.root, <.TouchableHighlight.reactClass)) {
      case List(filter) => filter
    }

    //when & then
    filter.props.onPress()
    assertPeopleScreen(renderer.root.children(0), props, pickerVisible = true)

    //when & then
    filter.props.onPress()
    assertPeopleScreen(renderer.root.children(0), props)
  }

  it should "filter data when onValueChange in Picker" in {
    //given
    val props = getPeopleScreenProps()
    val renderer = createTestRenderer(<(PeopleScreen())(^.wrapped := props)())
    val filter = inside(findComponents(renderer.root, <.TouchableHighlight.reactClass)) {
      case List(filter) => filter
    }
    filter.props.onPress()
    assertPeopleScreen(renderer.root.children(0), props, pickerVisible = true)
    val picker = inside(findComponents(renderer.root, <.Picker.reactClass)) {
      case List(picker) => picker
    }

    //when & then
    picker.props.onValueChange("male", 2)
    assertPeopleScreen(renderer.root.children(0), props.copy(
      data = props.data.copy(dataList = props.data.dataList.filter(_.gender == "male"))
    ), gender = "male", pickerVisible = true)
    
    //when & then
    picker.props.onValueChange("female", 3)
    assertPeopleScreen(renderer.root.children(0), props.copy(
      data = props.data.copy(dataList = props.data.dataList.filter(_.gender == "female"))
    ), gender = "female", pickerVisible = true)
    
    //when & then
    picker.props.onValueChange("n/a", 4)
    assertPeopleScreen(renderer.root.children(0), props.copy(
      data = props.data.copy(dataList = Nil)
    ), gender = "n/a", pickerVisible = true)
  }

  it should "dispatch actions if dataList is empty when mount" in {
    //given
    val dispatch = mockFunction[Any, Any]
    val actions = new Actions
    val props = {
      val props = getPeopleScreenProps(dispatch, actions = actions.actions)
      props.copy(data = props.data.copy(dataList = Nil))
    }
    val listFetchAction = PeopleListFetchAction(
      FutureTask("Fetching People", Future.successful(PeopleResp(Nil)))
    )
    actions.peopleListFetch.expects(dispatch).returning(listFetchAction)

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
    val comp = testRender(<(PeopleScreen())(^.wrapped := props)())
    val flatList = inside(findComponents(comp, <.FlatList.reactClass)) {
      case List(flatList) => flatList
    }
    val data = props.data.dataList.head
    
    //when
    val result = flatList.props.keyExtractor(data.asInstanceOf[js.Any])
    
    //then
    result shouldBe data.name
  }

  it should "dispatch action and show modal when press View Homeworld" in {
    //given
    val dispatch = mockFunction[Any, Any]
    val actions = new Actions
    val props = getPeopleScreenProps(dispatch, actions.actions)
    val data = props.data.dataList.head
    val renderer = createTestRenderer(<(PeopleScreen())(^.wrapped := props)())
    findComponents(renderer.root, <.Modal.reactClass) shouldBe Nil
    
    val itemMock = literal(item = data.asInstanceOf[js.Any])
    
    val flatList = inside(findComponents(renderer.root, <.FlatList.reactClass)) {
      case List(flatList) => flatList
    }
    val itemComp = renderItem(flatList, itemMock)
    val btn = inside(findComponents(itemComp, <.TouchableHighlight.reactClass)) {
      case List(btn) => btn
    }

    val planetData = PlanetData(
      name = "Tatooine",
      population = "200000",
      climate = "arid",
      gravity = "1 standard",
      terrain = "desert",
      diameter = "10465"
    )
    val homeWorldFetchAction = HomeWorldFetchAction(
      FutureTask("Fetching HomeWorld", Future.successful(planetData))
    )
    actions.homeWorldFetch.expects(*).onCall { value: String =>
      value shouldBe data.homeworld
      homeWorldFetchAction
    }

    //then
    dispatch.expects(homeWorldFetchAction).onCall { action: Any =>
      action shouldBe homeWorldFetchAction
    }
    
    //when
    btn.props.onPress()
    
    homeWorldFetchAction.task.future.map { _ =>
      //then
      val modal = inside(findComponents(renderer.root, <.Modal.reactClass)) {
        case List(modal) => modal
      }
      
      var closeModal: () => Unit = null
      assertNativeComponent(modal, <.Modal(^.animationType := AnimationType.slide)(), inside(_) {
        case List(homeworld) =>
          assertTestComponent(homeworld, homeWorldComp) { case HomeWorldProps(planet, resCloseModal) =>
            closeModal = resCloseModal
            planet shouldBe planetData
          }
      })

      //when
      closeModal()

      //then
      findComponents(renderer.root, <.Modal.reactClass) shouldBe Nil
    }
  }

  it should "render item" in {
    //given
    val props = getPeopleScreenProps()
    val comp = testRender(<(PeopleScreen())(^.wrapped := props)())
    val flatList = inside(findComponents(comp, <.FlatList.reactClass)) {
      case List(flatList) => flatList
    }
    val data = props.data.dataList.head
    val itemMock = literal(item = data.asInstanceOf[js.Any])

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
    val result = testRender(component)

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
  
  private def renderItem(flatList: TestInstance, itemMock: js.Dynamic): TestInstance = {
    val result = flatList.props.renderItem(itemMock.asInstanceOf[FlatListData[PeopleData]])
    createTestRenderer(result.asInstanceOf[ReactElement]).root
  }
  
  private def assertPeopleScreen(result: TestInstance,
                                 props: PeopleScreenProps,
                                 gender: String = "all",
                                 pickerVisible: Boolean = false): Assertion = {
    assertNativeComponent(result,
      <(containerComp())()(
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
