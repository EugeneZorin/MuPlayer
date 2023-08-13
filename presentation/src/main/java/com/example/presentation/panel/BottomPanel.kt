package com.example.presentation.panel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R

@Composable
fun BottomPanel(){


    BottomAppBar (
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(25.dp))
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = Modifier.fillMaxWidth(0.2f),
                onClick = {  }
            ) {
                Text(
                    text = "Setting",
                    fontFamily = FontFamily(Font(R.font.irish_grover_regular)),
                    fontSize = 18.sp
                )
            }
            IconButton(
                modifier = Modifier.fillMaxWidth(0.2f),
                onClick = {

                }
            ) {
                Text(
                    text = "Home",
                    fontFamily = FontFamily(Font(R.font.irish_grover_regular)),
                    fontSize = 18.sp
                )
            }
            IconButton(
                modifier = Modifier.fillMaxWidth(0.3f),
                onClick = { }
            ) {
                Text(
                    text = "Albums",
                    fontFamily = FontFamily(Font(R.font.irish_grover_regular)),
                    fontSize = 18.sp
                )
            }
        }
    }

}

