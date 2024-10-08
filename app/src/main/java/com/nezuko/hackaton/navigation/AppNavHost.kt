package com.nezuko.hackaton.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    startDestination: Screen = HomeScreen()
) {
    Navigator(screen = startDestination) { navigator ->
        SlideTransition(navigator = navigator)
    }
}