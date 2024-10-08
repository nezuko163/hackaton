package com.nezuko.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PermissionRoute(
    modifier: Modifier = Modifier,
    onAllowedButtonClick: () -> Unit,
    onDismissButtonClick: () -> Unit,
) {
    PermissionScreen(
        modifier = modifier,
        onAllowedButtonClick = onAllowedButtonClick,
        onDismissButtonClick = onDismissButtonClick
    )
}