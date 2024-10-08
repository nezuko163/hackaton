package com.nezuko.face.composables

import android.net.Uri
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import java.util.concurrent.Executor

@Composable
fun CameraView(
    modifier: Modifier = Modifier,
    lifecycleOwner: LifecycleOwner,
    onCapture: (Uri) -> Unit

) {
    val context = LocalContext.current

    val cameraExecutor: Executor = ContextCompat.getMainExecutor(context)
    var imageCapture: ImageCapture? by remember { mutableStateOf(null) }

    Box(modifier = modifier) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { ctx ->
                val cameraProviderFuture = ProcessCameraProvider.getInstance(ctx)
                val previewView = PreviewView(ctx)

                cameraProviderFuture.addListener(
                    {
                        val cameraProvider = cameraProviderFuture.get()
                        val preview = Preview.Builder().build().also {
                            it.setSurfaceProvider(previewView.surfaceProvider)
                        }
                        imageCapture = ImageCapture.Builder().build()
                        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
                        cameraProvider.bindToLifecycle(
                            lifecycleOwner,
                            cameraSelector,
                            preview,
                            imageCapture
                        )
                    },
                    cameraExecutor
                )

                previewView
            }
        )
    }
}