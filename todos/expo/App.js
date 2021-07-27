import React from 'react';
import { StyleSheet, Text, View } from 'react-native';

import {TodoApp} from './target/scala-2.13/scalajs-bundler/main/scommons-examples-mobile-todos-expo-fastopt';

const App = TodoApp.apply()

export default () => {
  
  return <App />
}
