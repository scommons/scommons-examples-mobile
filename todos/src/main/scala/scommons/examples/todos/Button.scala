package scommons.examples.todos

import scommons.react._
import scommons.reactnative._

import scala.scalajs.js

case class ButtonProps(submitTodo: () => Unit)

object Button extends FunctionComponent[ButtonProps] {

  protected def render(compProps: Props): ReactElement = {
    val props = compProps.wrapped
    
    <.View(^.rnStyle := styles.buttonContainer)(
      <.TouchableHighlight(
        ^.rnStyle := styles.button,
        ^.underlayColor := "#efefef",
        ^.onPress := props.submitTodo
      )(
        <.Text(^.rnStyle := styles.submit)(
          "Submit"
        )
      )
    )
  }

  private[todos] lazy val styles = StyleSheet.create(Styles)
  
  private[todos] object Styles extends js.Object {
    val buttonContainer: Style = new ViewStyle {
      override val alignItems = "flex-end"
    }
    val button: Style = new Style {
      override val height = 50
      override val paddingLeft = 20
      override val paddingRight = 20
      override val backgroundColor = "#ffffff"
      override val width = 200
      override val marginRight = 20
      override val marginTop = 15
      override val borderWidth = 1
      override val borderColor = "rgba(0,0,0,.1)"
      override val justifyContent = "center"
      override val alignItems = "center"
    }
    val submit: Style = new TextStyle {
      override val color = "#666666"
      override val fontWeight = "600"
    }
  }
}
