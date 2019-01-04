package scommons.react

import io.github.shogowada.scalajs.reactjs.classes.ReactClass

trait UiComponent[T] {

  type PropsType = T

  def reactClass: ReactClass
}
