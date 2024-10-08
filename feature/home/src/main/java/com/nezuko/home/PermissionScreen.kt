package com.nezuko.home

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nezuko.ui.theme.Spacing

@Composable
fun PermissionScreen(
    modifier: Modifier = Modifier,
    onAllowedButtonClick: () -> Unit,
    onDismissButtonClick: () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize()
            .padding(Spacing.default.extraLarge)) {
        Text(
            text = "Согласие на обработку персональных данных",
            fontSize = 35.sp,
            modifier = Modifier.align(
                Alignment.Center
            )
        )

        Column(modifier = Modifier.align(Alignment.BottomCenter)) {
            Button(
                onClick = onAllowedButtonClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {
                Text(
                    text = "Разрешить",
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.padding(Spacing.default.small))

            Button(
                onClick = onDismissButtonClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .border(2.dp,Color.Blue)
                ,
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(
                    text = "Отклонить",
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
            }
        }
    }
}