package com.nezuko.face

import android.Manifest
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.nezuko.domain.Constants.REQUEST_CODE_CAMERA
import com.nezuko.ui.util.permissionLauncher
import org.koin.androidx.compose.koinViewModel

const val TAG = "FACE_ROUTE"

@Composable
fun FaceRoute(
    onCapture: (Uri) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FaceViewModel = koinViewModel()
) {

    val cameraPermission by viewModel.cameraPermission.collectAsState()
    var isFirstLoading by rememberSaveable { mutableStateOf(true) }

    val launcher = permissionLauncher(
        onGranted = { viewModel.onPermissionRequest(REQUEST_CODE_CAMERA, true) },
        onFailure = { viewModel.onPermissionRequest(REQUEST_CODE_CAMERA, false) }
    )

    LaunchedEffect(isFirstLoading) {
        if (!isFirstLoading) return@LaunchedEffect
        if (!cameraPermission) {
            launcher.launch(Manifest.permission.CAMERA)
        }
    }

    LaunchedEffect(cameraPermission) {
        if (isFirstLoading) {
            Log.i(TAG, "FaceRoute: cameraPermission = $cameraPermission")
        }
        isFirstLoading = false
    }

    if (cameraPermission) {
        FaceScreen(onCapture = onCapture)
    } else {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center,
        ) {
            Text("123")
        }
    }
}