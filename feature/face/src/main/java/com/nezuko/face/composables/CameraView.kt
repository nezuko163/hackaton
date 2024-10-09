package com.nezuko.face.composables

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.core.impl.OptionsBundle
import androidx.camera.core.impl.PreviewConfig
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.nezuko.face.R
import com.nezuko.ui.theme.Spacing
import java.util.concurrent.Executor


private const val TAG = "CAMERA_VIEW"

@Composable
fun CameraView(
    modifier: Modifier = Modifier,
    lifecycleOwner: LifecycleOwner,
    onCapture: (Uri) -> Unit

) {
    val context = LocalContext.current
    var cameraSelector by remember { mutableStateOf(CameraSelector.DEFAULT_FRONT_CAMERA) }
    val cameraExecutor: Executor = ContextCompat.getMainExecutor(context)
    var imageCapture: ImageCapture? by remember { mutableStateOf(null) }
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    var cameraProvider by remember { mutableStateOf<ProcessCameraProvider?>(null) }
    val previewView = remember { PreviewView(context) }

    Log.i(TAG, "CameraView: $cameraSelector")

    fun bindCamera() {
        cameraProvider?.run {
            unbindAll()
            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(previewView.surfaceProvider)
            }

            imageCapture = ImageCapture.Builder()
                .setTargetRotation(previewView.display.rotation)
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                .build()

            bindToLifecycle(
                lifecycleOwner,
                cameraSelector,
                preview,
                imageCapture
            )
        }
    }

    LaunchedEffect(cameraProviderFuture) {
        cameraProvider = cameraProviderFuture.get()
    }

    LaunchedEffect(cameraSelector) {
        bindCamera()
    }

    Box(modifier = modifier) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { ctx ->
                Log.i(TAG, "CameraView: ")

                previewView
            }
        )

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.Black)
                .padding(Spacing.default.extraLarge)
                .border(3.dp, Color.White),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { Toast.makeText(context, "ee", Toast.LENGTH_SHORT).show() }) {
                Icon(
                    painter = painterResource(id = R.drawable.energy),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp),
                    tint = Color.White
                )
            }
            IconButton(onClick = { Toast.makeText(context, "ee", Toast.LENGTH_SHORT).show() }) {
                Icon(
                    painter = painterResource(id = R.drawable.shutter),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp), tint = Color.White

                )
            }
            IconButton(onClick = {
                cameraSelector = if (cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA) {
                    CameraSelector.DEFAULT_FRONT_CAMERA
                } else {
                    CameraSelector.DEFAULT_BACK_CAMERA
                }
                Toast.makeText(context, cameraSelector.toString(), Toast.LENGTH_SHORT).show()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.switch_camera),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp),
                    tint = Color.White

                )
            }
        }
    }
}