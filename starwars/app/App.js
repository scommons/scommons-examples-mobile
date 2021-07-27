import React from 'react';
import { StyleSheet, Text, View } from 'react-native';

import {StarWarsApp} from './target/scala-2.13/scalajs-bundler/main/scommons-examples-mobile-starwars-app-fastopt';

const App = StarWarsApp.apply()

export default () => {
  
  return <App />
}
