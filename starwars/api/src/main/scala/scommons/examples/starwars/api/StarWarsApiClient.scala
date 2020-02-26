package scommons.examples.starwars.api

import scommons.api.http.ApiHttpClient
import scommons.examples.starwars.api.people._

import scala.concurrent.Future

class StarWarsApiClient(client: ApiHttpClient)
  extends PeopleApi {

  ////////////////////////////////////////////////////////////////////////////////////////
  // people api

  def getPeople: Future[PeopleResp] = {
    client.execGet[PeopleResp]("https://swapi.co/api/people/")
  }
}
