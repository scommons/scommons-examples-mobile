package scommons.examples.todos

import org.scalatest.Succeeded
import scommons.react.test.TestSpec
import scommons.react.test.util.ShallowRendererUtils
import scommons.reactnative._

class TodoAppSpec extends TestSpec with ShallowRendererUtils {

  it should "update input text in state when inputChange" in {
    //given
    val renderer = createRenderer()
    var comp = shallowRender(renderer, <(TodoApp()).empty)
    val inputProps = findComponentProps(comp, Input)
    inputProps.inputValue shouldBe ""
    val text = "updated text"

    //when
    inputProps.inputChange(text)
    
    //then
    comp = shallowRender(renderer, <(TodoApp()).empty)
    findComponentProps(comp, Input).inputValue shouldBe text
  }
  
  it should "add todo, clear input and increment todoId when submitTodo" in {
    //given
    val renderer = createRenderer()
    var comp = shallowRender(renderer, <(TodoApp()).empty)
    findComponentProps(comp, TodoList).todos shouldBe Nil

    //when & then
    val todo1 = "todo1"
    findComponentProps(comp, Input).inputChange(todo1)
    findComponentProps(comp, Button).submitTodo()
    comp = shallowRender(renderer, <(TodoApp()).empty)
    findComponentProps(comp, Input).inputValue shouldBe ""
    findComponentProps(comp, TodoList).todos shouldBe List(
      TodoData(1, todo1, complete = false)
    )
    
    //when & then
    val todo2 = "todo2"
    findComponentProps(comp, Input).inputChange(todo2)
    findComponentProps(comp, Button).submitTodo()
    comp = shallowRender(renderer, <(TodoApp()).empty)
    findComponentProps(comp, Input).inputValue shouldBe ""
    findComponentProps(comp, TodoList).todos shouldBe List(
      TodoData(1, todo1, complete = false),
      TodoData(2, todo2, complete = false)
    )
  }
  
  it should "delete todo from state when deleteTodo" in {
    //given
    val renderer = createRenderer()
    var comp = shallowRender(renderer, <(TodoApp()).empty)

    val todo1 = "todo1"
    findComponentProps(comp, Input).inputChange(todo1)
    findComponentProps(comp, Button).submitTodo()
    comp = shallowRender(renderer, <(TodoApp()).empty)
    findComponentProps(comp, Input).inputValue shouldBe ""
    findComponentProps(comp, TodoList).todos shouldBe List(
      TodoData(1, todo1, complete = false)
    )
    
    val todo2 = "todo2"
    findComponentProps(comp, Input).inputChange(todo2)
    findComponentProps(comp, Button).submitTodo()
    comp = shallowRender(renderer, <(TodoApp()).empty)
    findComponentProps(comp, Input).inputValue shouldBe ""
    findComponentProps(comp, TodoList).todos shouldBe List(
      TodoData(1, todo1, complete = false),
      TodoData(2, todo2, complete = false)
    )

    //when & then
    findComponentProps(comp, TodoList).deleteTodo(1)
    comp = shallowRender(renderer, <(TodoApp()).empty)
    findComponentProps(comp, TodoList).todos shouldBe List(
      TodoData(2, todo2, complete = false)
    )
    
    //when & then
    findComponentProps(comp, TodoList).deleteTodo(2)
    comp = shallowRender(renderer, <(TodoApp()).empty)
    findComponentProps(comp, TodoList).todos shouldBe Nil
  }
  
  it should "toggle todo complete state when toggleComplete" in {
    //given
    val renderer = createRenderer()
    var comp = shallowRender(renderer, <(TodoApp()).empty)

    val todo1 = "todo1"
    findComponentProps(comp, Input).inputChange(todo1)
    findComponentProps(comp, Button).submitTodo()
    comp = shallowRender(renderer, <(TodoApp()).empty)
    findComponentProps(comp, Input).inputValue shouldBe ""
    findComponentProps(comp, TodoList).todos shouldBe List(
      TodoData(1, todo1, complete = false)
    )
    
    val todo2 = "todo2"
    findComponentProps(comp, Input).inputChange(todo2)
    findComponentProps(comp, Button).submitTodo()
    comp = shallowRender(renderer, <(TodoApp()).empty)
    findComponentProps(comp, Input).inputValue shouldBe ""
    findComponentProps(comp, TodoList).todos shouldBe List(
      TodoData(1, todo1, complete = false),
      TodoData(2, todo2, complete = false)
    )

    //when & then
    findComponentProps(comp, TodoList).toggleComplete(1)
    comp = shallowRender(renderer, <(TodoApp()).empty)
    findComponentProps(comp, TodoList).todos shouldBe List(
      TodoData(1, todo1, complete = true),
      TodoData(2, todo2, complete = false)
    )
    
    //when & then
    findComponentProps(comp, TodoList).toggleComplete(1)
    comp = shallowRender(renderer, <(TodoApp()).empty)
    findComponentProps(comp, TodoList).todos shouldBe List(
      TodoData(1, todo1, complete = false),
      TodoData(2, todo2, complete = false)
    )
    
    //when & then
    findComponentProps(comp, TodoList).toggleComplete(2)
    comp = shallowRender(renderer, <(TodoApp()).empty)
    findComponentProps(comp, TodoList).todos shouldBe List(
      TodoData(1, todo1, complete = false),
      TodoData(2, todo2, complete = true)
    )
  }
  
  it should "change visible todos when setType" in {
    //given
    val renderer = createRenderer()
    var comp = shallowRender(renderer, <(TodoApp()).empty)

    val todo1 = "todo1"
    findComponentProps(comp, Input).inputChange(todo1)
    findComponentProps(comp, Button).submitTodo()
    findComponentProps(comp, TodoList).toggleComplete(1)
    comp = shallowRender(renderer, <(TodoApp()).empty)
    findComponentProps(comp, Input).inputValue shouldBe ""
    findComponentProps(comp, TodoList).todos shouldBe List(
      TodoData(1, todo1, complete = true)
    )
    
    val todo2 = "todo2"
    findComponentProps(comp, Input).inputChange(todo2)
    findComponentProps(comp, Button).submitTodo()
    comp = shallowRender(renderer, <(TodoApp()).empty)
    findComponentProps(comp, Input).inputValue shouldBe ""
    findComponentProps(comp, TodoList).todos shouldBe List(
      TodoData(1, todo1, complete = true),
      TodoData(2, todo2, complete = false)
    )

    //when & then
    findComponentProps(comp, TabBar).setType(TodoType.Active)
    comp = shallowRender(renderer, <(TodoApp()).empty)
    findComponentProps(comp, TodoList).todos shouldBe List(
      TodoData(2, todo2, complete = false)
    )
    
    //when & then
    findComponentProps(comp, TabBar).setType(TodoType.Complete)
    comp = shallowRender(renderer, <(TodoApp()).empty)
    findComponentProps(comp, TodoList).todos shouldBe List(
      TodoData(1, todo1, complete = true)
    )
    
    //when & then
    findComponentProps(comp, TabBar).setType(TodoType.All)
    comp = shallowRender(renderer, <(TodoApp()).empty)
    findComponentProps(comp, TodoList).todos shouldBe List(
      TodoData(1, todo1, complete = true),
      TodoData(2, todo2, complete = false)
    )
  }
  
  it should "render component" in {
    //given
    val component = <(TodoApp())()()
    
    //when
    val result = shallowRender(component)
    
    //then
    assertNativeComponent(result, <.View(^.rnStyle := TodoApp.styles.container)(), {
      case List(scrollViewElem, tabBarElem) =>
        assertNativeComponent(scrollViewElem,
          <.ScrollView(
            ^.rnStyle := TodoApp.styles.content,
            ^.keyboardShouldPersistTaps := "always"
          )(), {
            case List(headingElem, inputElem, todoListElem, buttonElem) =>
              assertComponent(headingElem, Heading) { _: Unit =>
                Succeeded
              }
              assertComponent(inputElem, Input) { case InputProps(inputValue, _) =>
                inputValue shouldBe ""
              }
              assertComponent(todoListElem, TodoList) { case TodoListProps(_, _, todos) =>
                todos shouldBe Nil
              }
              assertComponent(buttonElem, Button) { case ButtonProps(_) =>
                Succeeded
              }
          }
        )
        assertComponent(tabBarElem, TabBar) { case TabBarProps(_, todoType) =>
          todoType shouldBe TodoType.All
        }
    })
  }
}
