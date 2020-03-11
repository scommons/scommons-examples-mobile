package scommons.examples.starwars.api.planet

import play.api.libs.json._

case class PlanetData(name: String,
                      population: String,
                      climate: String,
                      gravity: String,
                      terrain: String,
                      diameter: String)

object PlanetData {

  implicit val jsonFormat: Format[PlanetData] = Json.format[PlanetData]
}
