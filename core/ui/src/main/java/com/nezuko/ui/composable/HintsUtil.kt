package com.nezuko.ui.composable

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nezuko.ui.theme.LightBlue
import com.nezuko.ui.theme.Spacing

val TAG = "HINT_UTIL"

@Composable
fun ShowHint(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    shouldShow: Boolean = true
) {
    if (shouldShow) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .clickable { onClick() }
                .background(color = Color.Black.copy(alpha = 0.6f)),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(40.dp))
                    .background(LightBlue)

            ) {
                Text(
                    modifier = Modifier
                        .padding(Spacing.default.large),
                    text = text,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun ScreenWithHint(
    modifier: Modifier = Modifier,
    screen: @Composable () -> Unit,
    text: String,
    onClick: () -> Unit,
    shouldShowHint: Boolean = true
) {
    Log.i(TAG, "ScreenWithHint: should = $shouldShowHint")
    if (shouldShowHint) {
        Box(modifier = modifier) {
            screen()

            ShowHint(text = text, onClick = onClick)
        }
    } else {
        screen()
    }
}