package scommons.examples.starwars.people

import scommons.examples.starwars.api.people.PeopleData
import scommons.examples.starwars.people.PeopleActions._
import scommons.react.test.TestSpec

class PeopleStateReducerSpec extends TestSpec {

  private val reduce = PeopleStateReducer.apply _

  it should "return default state when state is None" in {
    //when & then
    reduce(None, "") shouldBe PeopleState()
  }

  it should "set dataList when PeopleListFetchedAction" in {
    //given
    val dataList = List(
      PeopleData(
        name = "Test",
        height = "180",
        birth_year = "1981",
        gender = "male",
        homeworld = "/some/homeworld/url"
      )
    )

    //when & then
    reduce(Some(PeopleState()), PeopleListFetchedAction(dataList)) shouldBe PeopleState(
      dataList = dataList
    )
  }
}
