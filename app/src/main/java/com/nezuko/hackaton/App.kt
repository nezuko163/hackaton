package com.nezuko.hackaton

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nezuko.hackaton.navigation.AppNavHost

@Composable
fun App(modifier: Modifier = Modifier) {
    AppNavHost(modifier)
}