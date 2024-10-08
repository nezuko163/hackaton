package com.nezuko.face

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.nezuko.face.composables.CameraView

@Composable
fun FaceScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    CameraView(
        lifecycleOwner = lifecycleOwner,
        onCapture = {}
    )
}