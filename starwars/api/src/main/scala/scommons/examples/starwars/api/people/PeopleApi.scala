package scommons.examples.starwars.api.people

import scala.concurrent.Future

trait PeopleApi {

  def getPeople: Future[PeopleResp]
}
