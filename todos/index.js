/**
 * @format
 */

import {AppRegistry} from 'react-native';
import {name as appName} from './app.json';

import {TodoApp} from './target/scala-2.12/scalajs-bundler/main/scommons-examples-mobile-todos-fastopt';

AppRegistry.registerComponent(appName, () => TodoApp.apply());
