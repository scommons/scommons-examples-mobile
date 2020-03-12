package scommons.examples.starwars.api.people

import play.api.libs.json._

case class PeopleData(name: String,
                      height: String,
                      birth_year: String,
                      gender: String,
                      homeworld: String)

object PeopleData {

  implicit val jsonFormat: Format[PeopleData] = Json.format[PeopleData]
}
