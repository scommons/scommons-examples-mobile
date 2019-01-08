
[![Build Status](https://travis-ci.org/scommons/scommons-examples-mobile.svg?branch=master)](https://travis-ci.org/scommons/scommons-examples-mobile)
[![Coverage Status](https://coveralls.io/repos/github/scommons/scommons-examples-mobile/badge.svg?branch=master)](https://coveralls.io/github/scommons/scommons-examples-mobile?branch=master)
[![Scala.js](https://www.scala-js.org/assets/badges/scalajs-0.6.17.svg)](https://www.scala-js.org)

## scommons-examples-mobile
Example applications that uses Scala Commons react-native components/modules

#### How to Build

To build and run tests use the following command:
```bash
sbt clean test
```

#### How to Build/Run example App on Emulator

First, build the application with the following command:
```bash
sbt "project scommons-examples-mobile-todos" fastOptJS
```

To run the application locally, use the following command:
```bash
react-native run-ios
```
