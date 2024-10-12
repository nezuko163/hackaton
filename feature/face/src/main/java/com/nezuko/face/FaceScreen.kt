package com.nezuko.face

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.nezuko.face.composables.CameraView
import com.nezuko.ui.composable.ScreenWithHint
import com.nezuko.ui.composable.ShowHint

@Composable
fun FaceScreen(
    modifier: Modifier = Modifier,
    onCapture: (Uri) -> Unit
) {
    var isFirstLoading by rememberSaveable { mutableStateOf(true) }
    val lifecycleOwner = LocalLifecycleOwner.current
    Log.i(TAG, "FaceScreen: $isFirstLoading")
    ScreenWithHint(
        screen = {
            CameraView(
                lifecycleOwner = lifecycleOwner,
                onCapture = onCapture
            )
        },
        text = "поместите лицо в овал\nвстаньте рядом с светом\nснимите головной убор",
        onClick = { isFirstLoading = false },
        shouldShowHint = isFirstLoading
    )


}

