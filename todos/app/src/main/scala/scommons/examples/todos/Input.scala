package scommons.examples.todos

import scommons.react._
import scommons.reactnative._

import scala.scalajs.js

case class InputProps(inputValue: String,
                      inputChange: String => Unit)

object Input extends FunctionComponent[InputProps] {

  protected def render(compProps: Props): ReactElement = {
    val props = compProps.wrapped
    
    <.View(^.rnStyle := styles.inputContainer)(
      <.TextInput(
        ^.rnStyle := styles.input,
        ^.value := props.inputValue,
        ^.placeholder := "What needs to be done?",
        ^.placeholderTextColor := "#CACACA",
        ^.selectionColor := "#666666",
        ^.onChangeText := props.inputChange
      )()
    )
  }

  private[todos] lazy val styles = StyleSheet.create(new Styles)
  private[todos] class Styles extends js.Object {
    import Style._
    
    val inputContainer: Style = new ViewStyle {
      override val marginLeft = 20
      override val marginRight = 20
      override val shadowOpacity = 0.2
      override val shadowRadius = 3
      override val shadowColor = "#000000"
      override val shadowOffset = new ShadowOffset {
        override val width = 2
        override val height = 2
      }
    }
    val input: Style = new TextStyle {
      override val height = 60
      override val backgroundColor = "#ffffff"
      override val paddingLeft = 10
      override val paddingRight = 10
    }
  }
}
