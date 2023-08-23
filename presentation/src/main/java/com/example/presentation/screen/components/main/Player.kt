package com.example.presentation.screen.components.main

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.R

@Preview(showBackground = true)
@Composable
fun Player() {

    var progress by remember { mutableFloatStateOf(0f) }

    Column(
        modifier = Modifier
            .background(Color(0xFFE1EAF2))
            .fillMaxWidth(),
    ) {

        Stripe(progress) { newProgress ->
            progress = newProgress
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {
                Text(text = "Названия песни ")
                Text(
                    text = "Исполнитель - Неизвестен",
                    color = Color.Gray
                )
            }

            Row(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.End
            ) {


                Box(
                    modifier = Modifier
                        .padding(horizontal = 20.dp),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_pause),
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                            .clickable { }
                    )
                }

                Box {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_skip_next),
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                            .clickable { }
                    )
                }
            }
        }
    }




}


@Composable
fun Stripe(progress: Float, onProgressChanged: (Float) -> Unit) {

    val offsetX = remember { mutableFloatStateOf(0f) }

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(10.dp)
            .pointerInput(Unit) {
                detectDragGestures { _, pan ->
                    offsetX.value += pan.x
                    val newProgress = (offsetX.value / size.width).coerceIn(0f, 1f)
                    onProgressChanged(newProgress)
                }
            }
    ) {

        val lineHeight = 4.dp.toPx()
        val circleSize = 30.dp.toPx()
        val progressWidth = size.width * progress
        val circleX = offsetX.value.coerceIn(0f, size.width)
        val circleY = lineHeight / 2
        val circleYOffset = (lineHeight - circleSize) / 512


        drawRect(
            color = Color.Gray,
            size = Size(size.width, lineHeight)
        )

        drawCircle(
            color = Color(0xFF982377),
            radius = circleSize / 4,
            center = Offset(circleX, circleY + circleYOffset)
        )


        drawRect(
            color = Color(0xFF982377),
            size = Size(progressWidth, lineHeight),
        )


    }
}









