package scommons.examples.todos

import io.github.shogowada.scalajs.reactjs.VirtualDOM
import org.scalatest._

class TodoAppSpec extends FlatSpec with Matchers {

  lazy val < : VirtualDOM.VirtualDOMElements = VirtualDOM.<
  
  it should "render component" in {
    //given
    val component = <(TodoApp())()()
    
    //then
    component should not be null
  }
}
