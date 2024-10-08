package com.nezuko.biometry

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nezuko.ui.theme.Spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BiometryScreen(
    modifier: Modifier = Modifier,
    onFingerPrintClick: () -> Unit,
    onFaceSnapshotClick: () -> Unit = {},
    onVoiceRecordClick: () -> Unit = {},
    onNavigateBack: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Биометрия",
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "назад"
                        )
                    }
                },
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = Spacing.default.large)
        ) {
            BiometryCard(
                cardData = BiometryCardData(
                    name = "Отпечаток пальца",
                    navigate = onFingerPrintClick,
                    icon = R.drawable.fingerprint,
                    isAnyData = true,
                    textForNotProvided = "Предоставьте отпечаток"
                )
            )

            Spacer(modifier = Modifier.padding(Spacing.default.medium))

            BiometryCard(
                cardData = BiometryCardData(
                    name = "Снимок лица",
                    navigate = onFaceSnapshotClick,
                    icon = R.drawable.face,
                    isAnyData = false,
                    textForNotProvided = "Предоставьте снимок лица"
                )
            )

            Spacer(modifier = Modifier.padding(Spacing.default.medium))

            BiometryCard(
                cardData = BiometryCardData(
                    name = "Запись голоса",
                    navigate = onVoiceRecordClick,
                    icon = R.drawable.voice,
                    isAnyData = false,
                    textForNotProvided = "Предоставьте запись голоса"
                )
            )
        }
    }
}

@Composable
private fun BiometryCard(
    modifier: Modifier = Modifier,
    cardData: BiometryCardData
) {
    ElevatedCard(
        onClick = cardData.navigate,
        modifier = modifier
            .fillMaxWidth()
            .height(135.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(modifier = Modifier.padding(Spacing.default.medium)) {
            Row(
                modifier = Modifier
            ) {
                Icon(
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.CenterVertically),
                    painter = painterResource(id = cardData.icon),
                    contentDescription = "z"
                )
                Text(
                    text = cardData.name,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            if (cardData.isAnyData) {
                Text(
                    text = "Данные предоставлены",
                    color = Color.Green,
                )
            } else {
                Text(
                    text = cardData.textForNotProvided,
                    color = Color.Blue,
                )
            }
        }
    }
}

data class BiometryCardData(
    val name: String,
    val navigate: () -> Unit,
    val icon: Int,
    val isAnyData: Boolean,
    val textForNotProvided: String
)