package scommons.examples.starwars.api.people

import scommons.examples.starwars.api.planet.PlanetData

import scala.concurrent.Future

trait PeopleApi {

  def getPeople: Future[PeopleResp]

  def getHomeWorld(url: String): Future[PlanetData]
}
