import definitions._
import scommons.sbtplugin.project.CommonModule
import scommons.sbtplugin.project.CommonModule.ideExcludedDirectories

lazy val `scommons-examples-mobile` = (project in file("."))
  .settings(CommonModule.settings: _*)
  .settings(ExamplesModule.settings: _*)
  .settings(
    ideExcludedDirectories += baseDirectory.value / "docs" / "_site"
  )
  .aggregate(
    `scommons-examples-mobile-todos`,
    `scommons-examples-mobile-starwars`
)

lazy val `scommons-examples-mobile-todos` = ExamplesTodos.definition

lazy val `scommons-examples-mobile-starwars-api-jvm` = StarWarsApi.jvm
lazy val `scommons-examples-mobile-starwars-api-js` = StarWarsApi.js
lazy val `scommons-examples-mobile-starwars-app` = StarWarsApp.definition
lazy val `scommons-examples-mobile-starwars` = (project in file("starwars")).aggregate(
  `scommons-examples-mobile-starwars-api-jvm`,
  `scommons-examples-mobile-starwars-api-js`,
  `scommons-examples-mobile-starwars-app`
)
