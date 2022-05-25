package scommons.examples.starwars.people

import scommons.examples.starwars.api.people.{PeopleApi, PeopleResp}
import scommons.examples.starwars.api.planet.PlanetData

import scala.concurrent.Future

//noinspection NotImplementedCode
class PeopleApiMock(
  getPeopleMock: () => Future[PeopleResp] = () => ???,
  getHomeWorldMock: String => Future[PlanetData] = _ => ???
) extends PeopleApi {

  override def getPeople: Future[PeopleResp] = getPeopleMock()

  override def getHomeWorld(url: String): Future[PlanetData] = getHomeWorldMock(url)
}
