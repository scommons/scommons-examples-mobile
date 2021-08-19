package scommons.examples.starwars.people

import io.github.shogowada.scalajs.reactjs.redux.Redux.Dispatch
import scommons.examples.starwars.Container
import scommons.examples.starwars.api.people.PeopleData
import scommons.examples.starwars.api.planet.PlanetData
import scommons.react._
import scommons.react.hooks._
import scommons.reactnative.FlatList._
import scommons.reactnative.Modal._
import scommons.reactnative._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js
import scala.util.Success

case class PeopleScreenProps(dispatch: Dispatch,
                             actions: PeopleActions,
                             data: PeopleState)

object PeopleScreen extends FunctionComponent[PeopleScreenProps] {

  private[people] var containerComp: UiComponent[Unit] = Container
  private[people] var homeWorldComp: UiComponent[HomeWorldProps] = HomeWorld

  private case class PeopleScreenState(gender: String = "all",
                                       modalData: Option[PlanetData] = None,
                                       pickerVisible: Boolean = false)
  
  protected def render(compProps: Props): ReactElement = {
    val (state, setState) = useStateUpdater(() => PeopleScreenState())
    
    val props = compProps.wrapped
    
    useEffect({ () =>
      if (props.data.dataList.isEmpty) {
        props.dispatch(props.actions.peopleListFetch(props.dispatch))
      }
      ()
    }, Nil)
    
    def openHomeWorld(url: String): Unit = {
      val action = props.actions.homeWorldFetch(url)
      props.dispatch(action)

      action.task.future.andThen {
        case Success(data) => setState(s => s.copy(modalData = Some(data)))
      }
    }
    
    val closeModal: js.Function0[Unit] = { () =>
      setState(s => s.copy(modalData = None))
    }
    
    val togglePicker: js.Function0[Unit] = { () =>
      setState(s => s.copy(pickerVisible = !s.pickerVisible))
    }
    
    def filter(gender: String): Unit = {
      setState(s => s.copy(gender = gender))
    }
    
    def renderItem(item: PeopleData): ReactElement = {
      <.View(^.rnStyle := styles.itemContainer)(
        <.Text(^.rnStyle := styles.name)(item.name),
        <.Text(^.rnStyle := styles.info)(s"Height: ${item.height}"),
        <.Text(^.rnStyle := styles.info)(s"Birth Year: ${item.birth_year}"),
        <.Text(^.rnStyle := styles.info)(s"Gender: ${item.gender}"),
        <.TouchableHighlight(^.onPress := { () =>
          openHomeWorld(item.homeworld)
        })(
          <.Text(^.rnStyle := styles.info)("View Homeworld")
        )
      )
    }

    val dataList =
      if (state.gender != "all") props.data.dataList.filter(_.gender == state.gender)
      else props.data.dataList
    
    <(containerComp())()(
      <.TouchableHighlight(^.rnStyle := styles.pickerToggleContainer, ^.onPress := togglePicker)(
        <.Text(^.rnStyle := styles.pickerToggle)(
          if (state.pickerVisible) "Close Filter"
          else "Open Filter"
        )
      ),
      
      <.FlatList(
        ^.flatListData := js.Array(dataList: _*),
        ^.keyExtractor := { item: PeopleData =>
          item.name
        },
        ^.renderItem := { data: FlatListData[PeopleData] =>
          renderItem(data.item)
        }
      )(),

      state.modalData.map { data =>
        <.Modal(^.animationType := AnimationType.slide)(
          <(homeWorldComp())(^.wrapped := HomeWorldProps(data, closeModal))()
        )
      },
      
      if (state.pickerVisible) Some(
        <.View(^.rnStyle := styles.pickerContainer)(
          <.Picker(
            ^.rnStyle := styles.picker,
            ^.selectedValue := state.gender,
            ^.onValueChange := { (item, _) =>
              filter(item)
            }
          )(
            <.PickerItem(^.rnStyle := styles.pickerAll, ^.label := "All", ^.value := "all")(),
            <.PickerItem(^.label := "Males", ^.value := "male")(),
            <.PickerItem(^.label := "Females", ^.value := "female")(),
            <.PickerItem(^.label := "Other", ^.value := "n/a")()
          )
        )
      ) else None
    )
  }

  private[people] lazy val styles = StyleSheet.create(new Styles)
  private[people] class Styles extends js.Object {
    import Style._
    import ViewStyle._

    val pickerToggleContainer: Style = new ViewStyle {
      override val padding = 25
      override val justifyContent = JustifyContent.center
      override val alignItems = AlignItems.center
    }
    val pickerToggle: Style = new ViewStyle {
      override val color = "#ffe81f"
    }
    
    val pickerContainer: Style = new ViewStyle {
      override val position = Position.absolute
      override val bottom = 0
      override val right = 0
      override val left = 0
    }
    val picker: Style = new ViewStyle {
      override val backgroundColor = "#ffe81f"
    }
    val pickerAll: Style = new ViewStyle {
      override val color = Color.yellow
    }

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
