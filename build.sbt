import definitions._
import scommons.sbtplugin.project.CommonModule

lazy val `scommons-examples-mobile` = (project in file("."))
  .settings(CommonModule.settings: _*)
  .settings(ExamplesModule.settings: _*)
  .settings(
    ideaExcludeFolders += s"${baseDirectory.value}/docs/_site"
  )
  .aggregate(
    `scommons-examples-mobile-todos`
)

lazy val `scommons-examples-mobile-todos` = ExamplesTodos.definition
