package com.nezuko.facehints

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.nezuko.domain.model.MLResponse
import com.nezuko.ui.util.getBitmapFromUri
import org.koin.androidx.compose.koinViewModel

@Composable
fun FaceHintsRoute(
    modifier: Modifier = Modifier,
    viewModel: FaceHintsViewModel = koinViewModel(),
    imageUri: Uri
) {
    var hints by remember { mutableStateOf<MLResponse?>(null) }

    LaunchedEffect(Unit) {
        hints = viewModel.analyse(uri = imageUri)
    }


    FaceHintsScreen(
        modifier = modifier,
        uriImage = imageUri,
        hints = hints
    )

}