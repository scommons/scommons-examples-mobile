package scommons.examples.starwars.api.people

import play.api.libs.json._

case class PeopleResp(results: List[PeopleData])

object PeopleResp {

  implicit val jsonFormat: Format[PeopleResp] = Json.format[PeopleResp]
}
