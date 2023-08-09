package com.example.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.presentation.R

@Preview(showBackground = true)
@Composable
fun StartScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            Text(
                text = "MuPlayer",
                fontFamily = FontFamily(Font(R.font.irish_grover_regular)),
                fontSize = 40.sp
            )
        }
    }
}