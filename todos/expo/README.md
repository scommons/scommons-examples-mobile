
## scommons-examples-mobile-todos-expo

Example expo `react-native` `Todos` application written in `Scala.js`
from a great book: [React Native in Action](https://github.com/dabit3/react-native-in-action)

![TodoApp](../../docs/images/TodoApp.png)

### How to Run App in your Device

* [live expo link](https://expo.io/@viktorpodzigun/todo-app)
* [mobile browser](https://scommons.org/scommons-examples-mobile/todos.html)
* [web emulator](https://scommons.org/scommons-examples-mobile/todos.browser.html)

### How to Build App locally

First, build the app with the following command:
```bash
sbt "project scommons-examples-mobile-todos-expo" fastOptJS
```

### How to Run App in Emulator

Use the following command:
```bash
cd todos/expo
expo start --ios
expo start --web
expo start --android
```

To build web-assets, use the following command:
```bash
cd todos/expo
expo build:web --no-pwa
```
