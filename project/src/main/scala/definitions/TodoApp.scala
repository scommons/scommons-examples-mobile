package definitions

import sbt.Keys._
import sbt._

object TodoApp extends ScalaJsModule {

  override val id = "scommons-examples-mobile-todos-app"

  override val base: File = file("todos/app")

  override def definition: Project = super.definition
    .settings(
      description := "Example TodoApp from the React Native in Action book, written in Scala.js"
    )

  override def runtimeDependencies: Def.Initialize[Seq[ModuleID]] = Def.setting {
    super.runtimeDependencies.value ++ Seq(
      // specify your custom runtime dependencies here
    )
  }
  
  override def testDependencies: Def.Initialize[Seq[ModuleID]] = Def.setting {
    super.testDependencies.value ++ Seq[ModuleID](
      // specify your custom test dependencies here
    ).map(_ % "test")
  }
}
