package com.nezuko.face.composables

import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.camera.core.CameraInfo
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCapture.OnImageSavedCallback
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.core.resolutionselector.ResolutionSelector
import androidx.camera.core.resolutionselector.ResolutionStrategy
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.video.FallbackStrategy
import androidx.camera.video.Quality
import androidx.camera.video.QualitySelector
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.nezuko.face.R
import com.nezuko.ui.theme.Spacing
import com.nezuko.ui.util.processImage
import com.nezuko.ui.util.saveBitmapToTempFile
import com.nezuko.ui.util.tempFile
import kotlinx.coroutines.launch
import java.util.concurrent.Executor


private const val TAG = "CAMERA_VIEW"


private val _SIZE = Size(800f, 1000f)
private val TOP_OFFSET = 300f
private var LEFT_OFFSET = 0f

@Composable
fun CameraView(
    modifier: Modifier = Modifier,
    lifecycleOwner: LifecycleOwner,
    onCapture: (Uri) -> Unit

) {
    Log.i(TAG, "CameraView: start")
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
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
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY)
                .setJpegQuality(100)
                .setResolutionSelector(
                    ResolutionSelector.Builder()
                        .setResolutionStrategy(
                            ResolutionStrategy(
                                android.util.Size(1920, 1080),
                                ResolutionStrategy.FALLBACK_RULE_NONE
                            )
                        )
                        .build()
                )
                .build()

            val camera = bindToLifecycle(
                lifecycleOwner,
                cameraSelector,
                preview,
                imageCapture
            )

            Log.i(TAG, "bindCamera: ${camera.cameraInfo.zoomState.value}")
        }
    }

    fun takePhoto() {
        val outputOptions: ImageCapture.OutputFileOptions =
            ImageCapture.OutputFileOptions.Builder(tempFile(context)).build()

        imageCapture?.run {
            takePicture(
                outputOptions,
                cameraExecutor,
                object : OnImageSavedCallback {
                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
//                        cameraProvider?.unbindAll()
                        val savedUri = outputFileResults.savedUri ?: return
                        val bitmap = BitmapFactory.decodeStream(
                            context.contentResolver.openInputStream(savedUri)
                        )
                        val croppedBitmap = processImage(
                            bitmap,
                            cameraSelector == CameraSelector.DEFAULT_FRONT_CAMERA,
                            left = LEFT_OFFSET,
                            top = TOP_OFFSET,
                            right = LEFT_OFFSET + _SIZE.width,
                            bottom = TOP_OFFSET + _SIZE.height
                        )
                        val croppedUri = saveBitmapToTempFile(context, croppedBitmap)
                        onCapture(croppedUri)
                        Log.i(TAG, "onImageSaved: $croppedUri")
                    }

                    override fun onError(exception: ImageCaptureException) {
                        TODO("Not yet implemented")
                    }

                }
            )
        }
    }

    LaunchedEffect(lifecycleOwner) {
        Log.i(TAG, "lifecycle: ${lifecycleOwner.lifecycle.currentState}")
        Log.i(TAG, "lifecycle: ${lifecycleOwner.lifecycle.currentStateFlow.value}")
    }

    LaunchedEffect(cameraProviderFuture) {
        cameraProvider = cameraProviderFuture.get()
        cameraProvider?.availableCameraInfos?.forEach { cameraInfo: CameraInfo? ->
            Log.i(TAG, "CameraView: ${cameraInfo?.lensFacing}")
        }
        coroutineScope.launch { bindCamera() }
    }

    LaunchedEffect(cameraSelector) {
        coroutineScope.launch { bindCamera() }
    }

    DisposableEffect(Unit) {
        onDispose {
            Log.i(TAG, "CameraView: 1")
        }
    }

    Column(modifier = modifier) {
        AndroidView(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .drawWithContent {
                    drawContent()

                    drawOval(
                        color = Color.White,
                        topLeft = Offset(
                            x = (size.width - _SIZE.width) / 2,
                            y = TOP_OFFSET
                        ).also { LEFT_OFFSET = (size.width - _SIZE.width) / 2 },
                        size = _SIZE,

                        style = Stroke(width = 5f)
                    )

//                    drawRect(Color.Black.copy(alpha = 0.4f))
//                    clipPath(Path().apply { addRect(Rect(Offset.Zero, size)) }) {
//                        drawOval(
//                            color = Color.Transparent,
//                            size = Size(400f, 400f)
//
//                        )
//                    }
                },
            factory = { ctx ->
                Log.i(TAG, "CameraView: ")

                previewView
            }
        )


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.Black)
                .padding(Spacing.default.extraLarge),
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
            IconButton(onClick = {
                Toast.makeText(context, "ee", Toast.LENGTH_SHORT).show()
                takePhoto()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.shutter),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp),
                    tint = Color.White

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



