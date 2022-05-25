package scommons.examples.starwars.people

import scommons.examples.starwars.api.people._
import scommons.examples.starwars.api.planet.PlanetData
import scommons.examples.starwars.people.PeopleActions._
import scommons.examples.starwars.people.PeopleActionsSpec._
import scommons.nodejs.test.AsyncTestSpec
import scommons.react.redux.task.FutureTask

import scala.concurrent.Future

class PeopleActionsSpec extends AsyncTestSpec {

  //noinspection TypeAnnotation
  class PeopleApi {
    val getPeople = mockFunction[Future[PeopleResp]]
    val getHomeWorld = mockFunction[String, Future[PlanetData]]

    val api = new PeopleApiMock(
      getPeopleMock = getPeople,
      getHomeWorldMock = getHomeWorld
    )
  }

  it should "dispatch PeopleListFetchedAction when peopleListFetch" in {
    //given
    val api = new PeopleApi
    val actions = new PeopleActionsTest(api.api)
    val dispatch = mockFunction[Any, Any]
    val dataList = List(mock[PeopleData])
    val expectedResp = PeopleResp(dataList)

    api.getPeople.expects()
      .returning(Future.successful(expectedResp))
    dispatch.expects(PeopleListFetchedAction(dataList))

    //when
    val PeopleListFetchAction(FutureTask(message, future)) =
      actions.peopleListFetch(dispatch)

    //then
    message shouldBe "Fetching People"
    future.map { resp =>
      resp shouldBe expectedResp
    }
  }
  
  it should "return HomeWorldFetchAction when homeWorldFetch" in {
    //given
    val api = new PeopleApi
    val actions = new PeopleActionsTest(api.api)
    val expectedResp = mock[PlanetData]
    val url = "/some/homeworld/url"

    api.getHomeWorld.expects(*).onCall { value: String =>
      value shouldBe url
      Future.successful(expectedResp)
    }

    //when
    val HomeWorldFetchAction(FutureTask(message, future)) =
      actions.homeWorldFetch(url)

    //then
    message shouldBe "Fetching HomeWorld"
    future.map { resp =>
      resp shouldBe expectedResp
    }
  }
}

object PeopleActionsSpec {

  private class PeopleActionsTest(api: PeopleApi)
    extends PeopleActions {

    protected def client: PeopleApi = api
  }
}
