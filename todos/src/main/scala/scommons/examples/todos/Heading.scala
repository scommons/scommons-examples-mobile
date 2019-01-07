package scommons.examples.todos

import io.github.shogowada.scalajs.reactjs.React
import io.github.shogowada.scalajs.reactjs.classes.ReactClass
import scommons.react.UiComponent
import scommons.reactnative._

import scala.scalajs.js

object Heading extends UiComponent[Unit] {
  
  def apply(): ReactClass = reactClass
  lazy val reactClass: ReactClass = createComp

  private def createComp: ReactClass = React.createClass[PropsType, Unit] { _ =>
    <.View(^.rnStyle := styles.header)(
      <.Text(^.rnStyle := styles.headerText)(
        "todos"
      )
    )
  }

  private[todos] lazy val styles = StyleSheet.create(Styles)

  private[todos] object Styles extends js.Object {
    val header: Style = new Style {
      override val marginTop = 80
    }
    val headerText: Style = new Style {
      override val textAlign = "center"
      override val fontSize = 72
      override val color = "rgba(175, 47, 47, 0.25)"
      override val fontWeight = "100"
    }
  }
}
