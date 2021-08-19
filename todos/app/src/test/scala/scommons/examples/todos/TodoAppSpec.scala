package scommons.examples.todos

import org.scalatest.Succeeded
import scommons.examples.todos.Button
import scommons.react.test._
import scommons.reactnative._
import scommons.reactnative.ScrollView._

class TodoAppSpec extends TestSpec with TestRendererUtils {

  it should "update input text in state when inputChange" in {
    //given
    val renderer = createTestRenderer(<(TodoApp()).empty)
    val inputProps = findComponentProps(renderer.root, Input)
    inputProps.inputValue shouldBe ""
    val text = "updated text"

    //when
    inputProps.inputChange(text)
    
    //then
    renderer.update(<(TodoApp()).empty)
    findComponentProps(renderer.root, Input).inputValue shouldBe text
  }
  
  it should "add todo, clear input and increment todoId when submitTodo" in {
    //given
    val renderer = createTestRenderer(<(TodoApp()).empty)
    findComponentProps(renderer.root, TodoList).todos shouldBe Nil

    //when & then
    val todo1 = "todo1"
    findComponentProps(renderer.root, Input).inputChange(todo1)
    findComponentProps(renderer.root, Button).submitTodo()
    renderer.update(<(TodoApp()).empty)
    findComponentProps(renderer.root, Input).inputValue shouldBe ""
    findComponentProps(renderer.root, TodoList).todos shouldBe List(
      TodoData(1, todo1, complete = false)
    )
    
    //when & then
    val todo2 = "todo2"
    findComponentProps(renderer.root, Input).inputChange(todo2)
    findComponentProps(renderer.root, Button).submitTodo()
    renderer.update(<(TodoApp()).empty)
    findComponentProps(renderer.root, Input).inputValue shouldBe ""
    findComponentProps(renderer.root, TodoList).todos shouldBe List(
      TodoData(1, todo1, complete = false),
      TodoData(2, todo2, complete = false)
    )
  }
  
  it should "delete todo from state when deleteTodo" in {
    //given
    val renderer = createTestRenderer(<(TodoApp()).empty)

    val todo1 = "todo1"
    findComponentProps(renderer.root, Input).inputChange(todo1)
    findComponentProps(renderer.root, Button).submitTodo()
    renderer.update(<(TodoApp()).empty)
    findComponentProps(renderer.root, Input).inputValue shouldBe ""
    findComponentProps(renderer.root, TodoList).todos shouldBe List(
      TodoData(1, todo1, complete = false)
    )
    
    val todo2 = "todo2"
    findComponentProps(renderer.root, Input).inputChange(todo2)
    findComponentProps(renderer.root, Button).submitTodo()
    renderer.update(<(TodoApp()).empty)
    findComponentProps(renderer.root, Input).inputValue shouldBe ""
    findComponentProps(renderer.root, TodoList).todos shouldBe List(
      TodoData(1, todo1, complete = false),
      TodoData(2, todo2, complete = false)
    )

    //when & then
    findComponentProps(renderer.root, TodoList).deleteTodo(1)
    renderer.update(<(TodoApp()).empty)
    findComponentProps(renderer.root, TodoList).todos shouldBe List(
      TodoData(2, todo2, complete = false)
    )
    
    //when & then
    findComponentProps(renderer.root, TodoList).deleteTodo(2)
    renderer.update(<(TodoApp()).empty)
    findComponentProps(renderer.root, TodoList).todos shouldBe Nil
  }
  
  it should "toggle todo complete state when toggleComplete" in {
    //given
    val renderer = createTestRenderer(<(TodoApp()).empty)

    val todo1 = "todo1"
    findComponentProps(renderer.root, Input).inputChange(todo1)
    findComponentProps(renderer.root, Button).submitTodo()
    renderer.update(<(TodoApp()).empty)
    findComponentProps(renderer.root, Input).inputValue shouldBe ""
    findComponentProps(renderer.root, TodoList).todos shouldBe List(
      TodoData(1, todo1, complete = false)
    )
    
    val todo2 = "todo2"
    findComponentProps(renderer.root, Input).inputChange(todo2)
    findComponentProps(renderer.root, Button).submitTodo()
    renderer.update(<(TodoApp()).empty)
    findComponentProps(renderer.root, Input).inputValue shouldBe ""
    findComponentProps(renderer.root, TodoList).todos shouldBe List(
      TodoData(1, todo1, complete = false),
      TodoData(2, todo2, complete = false)
    )

    //when & then
    findComponentProps(renderer.root, TodoList).toggleComplete(1)
    renderer.update(<(TodoApp()).empty)
    findComponentProps(renderer.root, TodoList).todos shouldBe List(
      TodoData(1, todo1, complete = true),
      TodoData(2, todo2, complete = false)
    )
    
    //when & then
    findComponentProps(renderer.root, TodoList).toggleComplete(1)
    renderer.update(<(TodoApp()).empty)
    findComponentProps(renderer.root, TodoList).todos shouldBe List(
      TodoData(1, todo1, complete = false),
      TodoData(2, todo2, complete = false)
    )
    
    //when & then
    findComponentProps(renderer.root, TodoList).toggleComplete(2)
    renderer.update(<(TodoApp()).empty)
    findComponentProps(renderer.root, TodoList).todos shouldBe List(
      TodoData(1, todo1, complete = false),
      TodoData(2, todo2, complete = true)
    )
  }
  
  it should "change visible todos when setType" in {
    //given
    val renderer = createTestRenderer(<(TodoApp()).empty)

    val todo1 = "todo1"
    findComponentProps(renderer.root, Input).inputChange(todo1)
    findComponentProps(renderer.root, Button).submitTodo()
    findComponentProps(renderer.root, TodoList).toggleComplete(1)
    renderer.update(<(TodoApp()).empty)
    findComponentProps(renderer.root, Input).inputValue shouldBe ""
    findComponentProps(renderer.root, TodoList).todos shouldBe List(
      TodoData(1, todo1, complete = true)
    )
    
    val todo2 = "todo2"
    findComponentProps(renderer.root, Input).inputChange(todo2)
    findComponentProps(renderer.root, Button).submitTodo()
    renderer.update(<(TodoApp()).empty)
    findComponentProps(renderer.root, Input).inputValue shouldBe ""
    findComponentProps(renderer.root, TodoList).todos shouldBe List(
      TodoData(1, todo1, complete = true),
      TodoData(2, todo2, complete = false)
    )

    //when & then
    findComponentProps(renderer.root, TabBar).setType(TodoType.Active)
    renderer.update(<(TodoApp()).empty)
    findComponentProps(renderer.root, TodoList).todos shouldBe List(
      TodoData(2, todo2, complete = false)
    )
    
    //when & then
    findComponentProps(renderer.root, TabBar).setType(TodoType.Complete)
    renderer.update(<(TodoApp()).empty)
    findComponentProps(renderer.root, TodoList).todos shouldBe List(
      TodoData(1, todo1, complete = true)
    )
    
    //when & then
    findComponentProps(renderer.root, TabBar).setType(TodoType.All)
    renderer.update(<(TodoApp()).empty)
    findComponentProps(renderer.root, TodoList).todos shouldBe List(
      TodoData(1, todo1, complete = true),
      TodoData(2, todo2, complete = false)
    )
  }
  
  it should "render component" in {
    //given
    val component = <(TodoApp())()()
    
    //when
    val result = testRender(component)
    
    //then
    assertNativeComponent(result, <.View(^.rnStyle := TodoApp.styles.container)(), {
      case List(scrollViewElem, tabBarElem) =>
        assertNativeComponent(scrollViewElem,
          <.ScrollView(
            ^.rnStyle := TodoApp.styles.content,
            ^.keyboardShouldPersistTaps := KeyboardShouldPersistTaps.always
          )(), {
            case List(headingElem, inputElem, todoListElem, buttonElem) =>
              assertTestComponent(headingElem, Heading) { _: Unit =>
                Succeeded
              }
              assertTestComponent(inputElem, Input) { case InputProps(inputValue, _) =>
                inputValue shouldBe ""
              }
              assertTestComponent(todoListElem, TodoList) { case TodoListProps(_, _, todos) =>
                todos shouldBe Nil
              }
              assertTestComponent(buttonElem, Button) { case ButtonProps(_) =>
                Succeeded
              }
          }
        )
        assertTestComponent(tabBarElem, TabBar) { case TabBarProps(_, todoType) =>
          todoType shouldBe TodoType.All
        }
    })
  }
}
