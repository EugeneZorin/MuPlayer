package com.example.presentation.screen.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun PlayerStripe() {


    var offsetX by remember { mutableFloatStateOf(0f) }
    val scope = rememberCoroutineScope()

    var elapsed by remember { mutableIntStateOf(0) }

    val targetTimeMillis = 5000
    val delayMillis = 16
    var iterations = targetTimeMillis / delayMillis

    var isPlaying by remember { mutableStateOf(true) }
    var isPaused by remember { mutableStateOf(true) }
    var progress by remember { mutableFloatStateOf(0f) }
    var moveProgress by remember { mutableIntStateOf(0) }

    var job: Job? = null

    LaunchedEffect(isPlaying) {
        if (isPlaying) {

            job = scope.launch {

                for ( time in moveProgress until iterations ) {

                    moveProgress = (iterations * progress).toInt()

                    if (isPaused) {
                        progress = time.toFloat() / iterations.toFloat()
                        delay(delayMillis.toLong())
                    }
                    Log.d("Coroutines", "SET: $moveProgress")

                }
            }
        } else {
            job?.cancel()
        }
    }


    Row {
        Button(onClick = {
            isPlaying = false
            isPaused = false
        }) {}
        Button(onClick = {
            isPlaying = true
            isPaused = true
        }) {}

        Text(text = elapsed.toString())
    }





    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(10.dp)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    offsetX += dragAmount.x

                    moveProgress = (offsetX / size.width).coerceIn(0f, 1f).toInt()
                    Log.d("MOVE", "SET: $moveProgress")
                }
            },

        ) {


        drawRect(
            color = Color.Gray,
            size = Size(size.width, 4.dp.toPx()),
        )

        drawCircle(
            color = Color(0xFF982377),
            radius = 30.dp.toPx() / 4,
            center = Offset(
                (size.width * progress + offsetX).coerceIn(0f, size.width),
                2.dp.toPx() + (4.dp.toPx() - 30.dp.toPx()) / 512
            )

        )


        drawRect(
            color = Color(0xFF982377),
            size = Size((size.width * progress + offsetX).coerceIn(0f, size.width), 4.dp.toPx()),
        )

    }


}

