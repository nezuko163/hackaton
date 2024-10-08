package com.nezuko.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeRoute(
    onTextClick: () -> Unit,
    modifier: Modifier = Modifier,

    // либо через koinViewModel сделать, либо передавать в функцию и создавать вью модель выше
    viewModel: HomeViewModel = viewModel()
) {
    HomeScreen(onTextClick = onTextClick)
}