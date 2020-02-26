package scommons.examples.starwars

import scommons.examples.starwars.api.people._
import scommons.examples.starwars.people.PeopleActions.PeopleListFetchAction
import scommons.examples.starwars.people.PeopleState
import scommons.react.redux.task.FutureTask
import scommons.react.test.TestSpec

import scala.concurrent.Future

class StarWarsStateReducerSpec extends TestSpec {

  it should "return initial state" in {
    //when
    val result = StarWarsStateReducer.reduce(None, "")

    //then
    inside(result) {
      case StarWarsState(
        currentTask,
        peopleState
        ) =>
        currentTask shouldBe None
        peopleState shouldBe PeopleState()
    }
  }

  it should "set currentTask when TaskAction" in {
    //given
    val initialState = StarWarsStateReducer.reduce(None, "")
    val task = FutureTask("test task", Future.successful(PeopleResp(List.empty[PeopleData])))
    initialState.currentTask shouldBe None

    //when
    val result = StarWarsStateReducer.reduce(Some(initialState), PeopleListFetchAction(task))

    //then
    result.currentTask shouldBe Some(task)
  }
}
