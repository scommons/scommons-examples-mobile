package scommons.examples.todos

import io.github.shogowada.scalajs.reactjs.React
import io.github.shogowada.scalajs.reactjs.VirtualDOM._
import io.github.shogowada.scalajs.reactjs.classes.ReactClass
import scommons.mobile.ui.NativeText._
import scommons.mobile.ui.NativeView._
import scommons.mobile.ui.Style._
import scommons.mobile.ui.{Style, StyleSheet}

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel(name = "TodoApp")
object TodoApp {

  @JSExport("apply")
  def apply(): ReactClass = reactClass
  
  lazy val reactClass: ReactClass = createComp

  private def createComp: ReactClass = React.createClass[Unit, Unit] { _ =>
    <.View(^.rnStyle := styles.container)(
      <.Text(^.rnStyle := styles.welcome)("Welcome to React Native!"),
      <.Text(^.rnStyle := styles.instructions)("To get started, edit TodoApp4"),
      <.Text(^.rnStyle := styles.instructions)(
        "Press Cmd+R to reload,\n" +
        "Cmd+D or shake for dev menu"
      )
    )
  }

  private[todos] lazy val styles: Styles = StyleSheet.create(new Styles {
    val container: Style = new Style {
      override val flex = 1
      override val justifyContent = "center"
      override val alignItems = "center"
      override val backgroundColor = "#F5FCFF"
    }
    val welcome: Style = new Style {
      override val fontSize = 20
      override val textAlign = "center"
      override val margin = 10
    }
    val instructions: Style = new Style {
      override val textAlign = "center"
      override val color = "#333333"
      override val marginBottom = 5
    }
  })
  
  private[todos] trait Styles extends js.Object {
    val container: Style
    val welcome: Style
    val instructions: Style
  }
}
