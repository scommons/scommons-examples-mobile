package scommons.examples.starwars.api

import scommons.api.http.ApiHttpClient
import scommons.examples.starwars.api.people._
import scommons.examples.starwars.api.planet.PlanetData

import scala.concurrent.Future

class StarWarsApiClient(client: ApiHttpClient)
  extends PeopleApi {

  ////////////////////////////////////////////////////////////////////////////////////////
  // people api

  def getPeople: Future[PeopleResp] = {
    client.execGet[PeopleResp]("https://swapi.dev/api/people/")
  }

  def getHomeWorld(url: String): Future[PlanetData] = {
    client.execGet[PlanetData](url)
  }
}
