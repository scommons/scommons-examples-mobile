
## scommons-examples-mobile-starwars
Example usages of react-native components/modules written in Scala.js.

Almost all examples are from an excellent book [React Native in Action](https://www.manning.com/books/react-native-in-action) by [Nader Dabit](https://github.com/dabit3)

![StarWarsApp](../../docs/images/StarWarsApp.png)

### How to run it in your device

* [live expo link](https://expo.io/@viktorpodzigun/starwars)
* [mobile browser](https://scommons.org/scommons-examples-mobile/starwars.html)
* [web emulator](https://scommons.org/scommons-examples-mobile/starwars.browser.html)

#### How to Build/Run App locally using Expo

First, build the application with the following command:
```bash
sbt "project scommons-examples-mobile-starwars-app" fastOptJS
```

To run the application locally, use the following command:
```bash
cd starwars/app
expo start --ios
expo start --web
expo start --android
```

To build web-assets, use the following command:
```bash
cd starwars/app
expo build:web --no-pwa
```
