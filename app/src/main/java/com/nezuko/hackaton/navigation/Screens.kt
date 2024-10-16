package com.nezuko.hackaton.navigation

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.nezuko.biometry.BiometryRoute
import com.nezuko.biometry.BiometryViewModel
import com.nezuko.face.FaceRoute
import com.nezuko.facehints.FaceHintsRoute
import com.nezuko.home.HomeRoute
import com.nezuko.home.HomeViewModel
import com.nezuko.home.PermissionRoute
import com.nezuko.voice.VoiceRoute
import org.koin.androidx.compose.koinViewModel

class HomeScreen : Screen {
    private val TAG = "SCREENS__"

    @Composable
    override fun Content() {
        val vm: HomeViewModel = koinViewModel()
        val navigator = LocalNavigator.currentOrThrow
        val shouldShowDataPermission by vm.shouldShowDataPermission.collectAsState(false)
        Log.i(TAG, "Content: $shouldShowDataPermission")
        HomeRoute(
            onTextClick = {
                if (!shouldShowDataPermission) navigator.push(PermissionScreen())
                else navigator.push(BiometryScreen())
                vm.edit(true)
            },
            viewModel = vm
        )
    }
}

class PermissionScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        PermissionRoute(
            onAllowedButtonClick = { navigator.replace(BiometryScreen()) },
            onDismissButtonClick = { TODO() }
        )
    }
}

class BiometryScreen : Screen {
    @Composable
    override fun Content() {
        val vm: BiometryViewModel = koinViewModel()
        val navigator = LocalNavigator.currentOrThrow
        val context = LocalContext.current
        BiometryRoute(
            viewModel = vm,
            onFingerPrintClick = {
                Toast.makeText(context, "отпечаток", Toast.LENGTH_SHORT).show()
            },
            onFaceSnapshotClick = { navigator.push(FaceScreen()) },
            onVoiceRecordClick = { navigator.push(VoiceScreen()) },
            onNavigateBack = navigator::pop,
        )
    }

}

class FaceScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        FaceRoute(
            onCapture = { uri ->

                navigator.push(FaceHintsScreen(uri))
            }
        )
    }
}

class VoiceScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        VoiceRoute(

        )
    }
}

class FaceHintsScreen(private val imageUri: Uri) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        FaceHintsRoute(imageUri = imageUri)
    }
}