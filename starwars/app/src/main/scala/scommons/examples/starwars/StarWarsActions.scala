package scommons.examples.starwars

import scommons.api.http.xhr.XhrApiHttpClient
import scommons.examples.starwars.api.StarWarsApiClient
import scommons.examples.starwars.people.PeopleActions

trait StarWarsActions
  extends PeopleActions

object StarWarsActions extends StarWarsActions {

  protected val client: StarWarsApiClient = {
    new StarWarsApiClient(new XhrApiHttpClient(""))
  }
}
