package com.nezuko.voice

import android.Manifest
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
import com.nezuko.domain.Constants.REQUEST_CODE_MICRO
import com.nezuko.ui.util.permissionLauncher
import org.koin.androidx.compose.koinViewModel

const val TAG = "VOICE_ROUTE"

@Composable
fun VoiceRoute(
    modifier: Modifier = Modifier,
    viewModel: VoiceViewModel = koinViewModel()
) {
    val microPermission by viewModel.microPermission.collectAsState()
    var isFirstLoading by rememberSaveable { mutableStateOf(true) }

    val launcher = permissionLauncher(
        onGranted = { viewModel.onPermissionRequest(REQUEST_CODE_MICRO, true) },
        onFailure = { viewModel.onPermissionRequest(REQUEST_CODE_MICRO, false) }
    )

    LaunchedEffect(isFirstLoading) {
        if (!isFirstLoading) return@LaunchedEffect
        if (!microPermission) {
            launcher.launch(Manifest.permission.RECORD_AUDIO)
        }
    }

    LaunchedEffect(microPermission) {
        if (isFirstLoading) {
            Log.i(TAG, "FaceRoute: cameraPermission = $microPermission")
        }
        isFirstLoading = false
    }

    if (microPermission) {
        VoiceScreen()
    } else {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center,
        ) {
            Text("123")
        }
    }
}