
## scommons-examples-mobile-todos-bare

Example bare `react-native` `Todos` application written in `Scala.js`
from a great book: [React Native in Action](https://github.com/dabit3/react-native-in-action)

![TodoApp](../../docs/images/TodoApp.png)

### How to Run App in your Device

* [live expo link](https://expo.io/@viktorpodzigun/todo-app)
* [mobile browser](https://scommons.github.io/scommons-examples-mobile/todos.html)
* [web emulator](https://scommons.github.io/scommons-examples-mobile/todos.browser.html)

### Initial setup

Install cocoapods:

```bash
cd todos/bare
gem install cocoapods
```

Install dependencies:

```bash
cd todos/bare/ios
pod install
```

To resolve errors/issues with the setup:
```bash
cd todos/bare
npx react-native doctor
```

### How to Build App locally

First, build the app with the following command:
```bash
sbt "project scommons-examples-mobile-todos-bare" fastOptJS
```

### How to Run App in iOS Emulator

Use the following command:
```bash
cd todos/bare
npx react-native run-ios
```
