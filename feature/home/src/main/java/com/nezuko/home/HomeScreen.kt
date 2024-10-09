package com.nezuko.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onTextClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clickable { onTextClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Установить биометрические данные",
            modifier = Modifier,
            )

    }
}