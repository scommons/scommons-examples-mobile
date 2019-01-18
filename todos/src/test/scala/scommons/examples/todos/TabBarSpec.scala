package scommons.examples.todos

import scommons.react.test.TestSpec
import scommons.react.test.raw.TestInstance
import scommons.react.test.util.TestRendererUtils
import scommons.reactnative._

class TabBarSpec extends TestSpec with TestRendererUtils {

  it should "call setType(All) when onPress All item" in {
    //given
    val setType = mockFunction[TodoType, Unit]
    val props = TabBarProps(setType, TodoType.Active)
    val comp = render(<(TabBar())(^.wrapped := props)())
    val item = findProps(comp, TabBarItem).head

    //then
    setType.expects(TodoType.All)

    //when
    item.onPress()
  }

  it should "call setType(Active) when onPress Active item" in {
    //given
    val setType = mockFunction[TodoType, Unit]
    val props = TabBarProps(setType, TodoType.All)
    val comp = render(<(TabBar())(^.wrapped := props)())
    val item = findProps(comp, TabBarItem)(1)

    //then
    setType.expects(TodoType.Active)

    //when
    item.onPress()
  }

  it should "call setType(Complete) when onPress Complete item" in {
    //given
    val setType = mockFunction[TodoType, Unit]
    val props = TabBarProps(setType, TodoType.All)
    val comp = render(<(TabBar())(^.wrapped := props)())
    val item = findProps(comp, TabBarItem)(2)

    //then
    setType.expects(TodoType.Complete)

    //when
    item.onPress()
  }

  it should "render component with selected All item" in {
    //given
    val setType = mockFunction[TodoType, Unit]
    val props = TabBarProps(setType, TodoType.All)
    val component = <(TabBar())(^.wrapped := props)()

    //when
    val result = render(component)

    //then
    assertTabBar(result, props)
  }
  
  it should "render component with selected Active item" in {
    //given
    val setType = mockFunction[TodoType, Unit]
    val props = TabBarProps(setType, TodoType.Active)
    val component = <(TabBar())(^.wrapped := props)()

    //when
    val result = render(component)

    //then
    assertTabBar(result, props)
  }
  
  it should "render component with selected Complete item" in {
    //given
    val setType = mockFunction[TodoType, Unit]
    val props = TabBarProps(setType, TodoType.Complete)
    val component = <(TabBar())(^.wrapped := props)()

    //when
    val result = render(component)

    //then
    assertTabBar(result, props)
  }
  
  private def assertTabBar(result: TestInstance, props: TabBarProps): Unit = {
    assertNativeComponent(result, <("View")(^.rnStyle := TabBar.styles.container)(), {
      case List(allElem, activeElem, completeElem) =>
        assertComponent(allElem, TabBarItem) { case TabBarItemProps(border, title, selected, _) =>
          border shouldBe false
          title shouldBe "All"
          selected shouldBe (props.`type` == TodoType.All)
        }
        assertComponent(activeElem, TabBarItem) { case TabBarItemProps(border, title, selected, _) =>
          border shouldBe true
          title shouldBe "Active"
          selected shouldBe (props.`type` == TodoType.Active)
        }
        assertComponent(completeElem, TabBarItem) { case TabBarItemProps(border, title, selected, _) =>
          border shouldBe true
          title shouldBe "Complete"
          selected shouldBe (props.`type` == TodoType.Complete)
        }
    })
  }
}
