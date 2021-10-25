import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import { registerRootComponent } from 'expo';

import {StarWarsApp} from './target/scala-2.13/scalajs-bundler/main/scommons-examples-mobile-starwars-app-opt';

const App = StarWarsApp.apply()

registerRootComponent(App);
