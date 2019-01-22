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
    `scommons-examples-mobile-todos`
)

lazy val `scommons-examples-mobile-todos` = ExamplesTodos.definition
