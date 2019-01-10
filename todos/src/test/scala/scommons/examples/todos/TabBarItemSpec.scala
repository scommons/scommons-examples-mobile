package scommons.examples.todos

import scommons.react.test.TestSpec
import scommons.react.test.raw.TestInstance
import scommons.react.test.util.TestRendererUtils
import scommons.reactnative._

import scala.scalajs.js

class TabBarItemSpec extends TestSpec with TestRendererUtils {

  it should "call onPress when onPress" in {
    //given
    val onPress = mockFunction[Unit]
    val props = TabBarItemProps(border = false, title = "Item", selected = false, onPress)
    val comp = render(<(TabBarItem())(^.wrapped := props)())
    val button = findComponents(comp, NativeTouchableHighlight).head
    
    //then
    onPress.expects()
    
    //when
    button.props.onPress()
  }
  
  it should "render simple item" in {
    //given
    val onPress = mockFunction[Unit]
    val props = TabBarItemProps(border = false, title = "Item", selected = false, onPress)
    val component = <(TabBarItem())(^.wrapped := props)()
    
    //when
    val result = render(component)
    
    //then
    assertTabBarItem(result, props,
      List(TabBarItem.styles.item),
      List(TabBarItem.styles.itemText)
    )
  }

  it should "render item with border" in {
    //given
    val onPress = mockFunction[Unit]
    val props = TabBarItemProps(border = true, title = "Item", selected = false, onPress)
    val component = <(TabBarItem())(^.wrapped := props)()

    //when
    val result = render(component)

    //then
    assertTabBarItem(result, props,
      List(TabBarItem.styles.item, TabBarItem.styles.border),
      List(TabBarItem.styles.itemText)
    )
  }

  it should "render selected item" in {
    //given
    val onPress = mockFunction[Unit]
    val props = TabBarItemProps(border = false, title = "Item", selected = true, onPress)
    val component = <(TabBarItem())(^.wrapped := props)()
    
    //when
    val result = render(component)
    
    //then
    assertTabBarItem(result, props,
      List(TabBarItem.styles.item, TabBarItem.styles.selected),
      List(TabBarItem.styles.itemText, TabBarItem.styles.bold)
    )
  }

  it should "render selected item with border" in {
    //given
    val onPress = mockFunction[Unit]
    val props = TabBarItemProps(border = true, title = "Item", selected = true, onPress)
    val component = <(TabBarItem())(^.wrapped := props)()

    //when
    val result = render(component)

    //then
    assertTabBarItem(result, props,
      List(TabBarItem.styles.item, TabBarItem.styles.selected, TabBarItem.styles.border),
      List(TabBarItem.styles.itemText, TabBarItem.styles.bold)
    )
  }

  private def assertTabBarItem(result: TestInstance,
                               props: TabBarItemProps,
                               itemStyle: List[Style],
                               textStyle: List[Style]): Unit = {
    
    assertNativeComponent(result,
      <("TouchableHighlight")(
        ^.rnStyle := js.Array(itemStyle: _*),
        ^.underlayColor := "#efefef"
      )(
        <("Text")(^.rnStyle := js.Array(textStyle: _*))(
          props.title
        )
      )
    )
  }
}
