package scommons.examples.todos

import io.github.shogowada.scalajs.reactjs.React
import io.github.shogowada.scalajs.reactjs.classes.ReactClass
import scommons.react.UiComponent
import scommons.reactnative._

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel(name = "TodoApp")
object TodoApp extends UiComponent[Unit] {

  @JSExport("apply")
  def apply(): ReactClass = reactClass
  lazy val reactClass: ReactClass = createComp

  private def createComp: ReactClass = React.createClass[PropsType, Unit] { _ =>
    <.View(^.rnStyle := styles.container)(
    )
  }

  private[todos] lazy val styles: Styles = StyleSheet.create(new Styles {
    val container: Style = new Style {
      override val flex = 1
      override val backgroundColor = "#f5f5f5"
    }
    val content: Style = new Style {
      override val flex = 1
      override val paddingTop = 60
    }
  })
  
  private[todos] trait Styles extends js.Object {
    val container: Style
    val content: Style
  }
}
