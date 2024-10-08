package com.nezuko.hackaton.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.nezuko.biometry.BiometryRoute
import com.nezuko.biometry.BiometryViewModel
import com.nezuko.face.FaceRoute
import com.nezuko.home.HomeRoute
import com.nezuko.home.HomeViewModel
import com.nezuko.home.PermissionRoute
import com.nezuko.voice.VoiceRoute
import org.koin.androidx.compose.koinViewModel

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        val vm: HomeViewModel = koinViewModel()
        val navigator = LocalNavigator.currentOrThrow
        HomeRoute(
            onTextClick = { navigator.push(PermissionScreen()) },
            viewModel = vm
        )
    }
}

class PermissionScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        PermissionRoute(
            onAllowedButtonClick = { navigator.replaceAll(BiometryScreen()) },
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

class FaceScreen: Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        FaceRoute(

        )
    }
}

class VoiceScreen: Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        VoiceRoute(

        )
    }
}