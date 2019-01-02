package scommons.examples

import io.github.shogowada.scalajs.reactjs.VirtualDOM
import org.scalatest.{FlatSpec, Matchers}

abstract class TestSpec extends FlatSpec
  with Matchers {

  lazy val < : VirtualDOM.VirtualDOMElements = VirtualDOM.<
  lazy val ^ : VirtualDOM.VirtualDOMAttributes = VirtualDOM.^
}
