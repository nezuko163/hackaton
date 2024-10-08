package com.nezuko.biometry

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BiometryRoute(
    modifier: Modifier = Modifier,
    viewModel: BiometryViewModel,
    onFingerPrintClick: () -> Unit,
    onFaceSnapshotClick: () -> Unit,
    onVoiceRecordClick: () -> Unit,
    onNavigateBack: () -> Unit
) {
    BiometryScreen(
        modifier = modifier,
        onFaceSnapshotClick = onFaceSnapshotClick,
        onFingerPrintClick = onFingerPrintClick,
        onVoiceRecordClick = onVoiceRecordClick,
        onNavigateBack = onNavigateBack
    )
}