package com.nezuko.facehints

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.nezuko.ui.theme.Spacing

@Composable
fun FaceHintsScreen(
    modifier: Modifier = Modifier,
    uriImage: Uri,
    hints: String
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(Spacing.default.extraLarge))

        AsyncImage(
            modifier = Modifier
                .weight(0.5f)
                .padding(Spacing.default.medium),
            model = uriImage,
            contentDescription = "фотка",
        )

        Text(
            modifier = Modifier
                .weight(0.5f)
                .padding(Spacing.default.medium),
            text = hints,
        )
    }
}