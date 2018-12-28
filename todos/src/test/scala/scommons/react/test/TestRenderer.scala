package scommons.react.test

import io.github.shogowada.scalajs.reactjs.elements.ReactElement

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/**
  * Facade for React TestRenderer.
  * 
  * @see https://reactjs.netlify.com/docs/test-renderer.html
  */
@JSImport("react-test-renderer", JSImport.Namespace)
@js.native
object TestRenderer extends js.Object {

  def create(element: ReactElement): TestRenderer = js.native
}

@js.native
trait TestRenderer extends js.Object {

  //def toJSON(): js.Object = js.native
  
  val root: TestInstance = js.native
}

@js.native
trait TestInstance extends js.Object {

  val `type`: js.Any = js.native
  val props: js.Object with js.Dynamic = js.native
  val children: js.Array[TestInstance] = js.native
}
