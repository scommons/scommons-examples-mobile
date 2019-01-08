package scommons.react.test

import io.github.shogowada.scalajs.reactjs.VirtualDOM
import org.scalamock.scalatest.MockFactory
import org.scalatest.{FlatSpec, Matchers}

abstract class TestSpec extends FlatSpec
  with Matchers
  with MockFactory {

  lazy val < : VirtualDOM.VirtualDOMElements = VirtualDOM.<
  lazy val ^ : VirtualDOM.VirtualDOMAttributes = VirtualDOM.^
}
