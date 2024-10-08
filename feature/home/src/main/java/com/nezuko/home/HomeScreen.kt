package com.nezuko.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onTextClick: () -> Unit,
) {
    Box(modifier.fillMaxSize()) {
        Text(
            text = "Установить биометрические данные",
            modifier = Modifier
                .fillMaxSize()
                .clickable { onTextClick() },
            textAlign = TextAlign.Center
        )
    }
}