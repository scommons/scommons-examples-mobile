import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import { registerRootComponent } from 'expo';

import {TodoApp} from './target/scala-2.13/scalajs-bundler/main/scommons-examples-mobile-todos-expo-opt';

const App = TodoApp.apply()

registerRootComponent(App);
