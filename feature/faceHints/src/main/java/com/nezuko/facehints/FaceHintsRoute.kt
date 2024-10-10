package com.nezuko.facehints

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.nezuko.ui.util.getBitmapFromUri
import org.koin.androidx.compose.koinViewModel

@Composable
fun FaceHintsRoute(
    modifier: Modifier = Modifier,
    viewModel: FaceHintsViewModel = koinViewModel(),
    imageUri: Uri
) {
    val hints by remember { mutableStateOf("подсказки для фотки") }

    if (imageUri.toString().isNotEmpty()) {
        FaceHintsScreen(
            modifier = modifier,
            uriImage = imageUri,
            hints = hints
        )
    }
}