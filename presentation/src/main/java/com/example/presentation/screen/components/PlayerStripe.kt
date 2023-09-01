package com.example.presentation.screen.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun PlayerStripe() {


    val time = 5000


    var offsetX by remember { mutableFloatStateOf(0f) }
    val scope = rememberCoroutineScope()

    val animated = remember { Animatable(initialValue = 0f) }

    var isPlaying by remember { mutableStateOf(true) }
    var progress by remember { mutableFloatStateOf(0f) }
    var elapsed by remember { mutableIntStateOf(0) }
    var timeObserver by remember { mutableLongStateOf(0L) }


    Row {
        Button(onClick = {

            isPlaying = false
        }) {}
        Button(onClick = {
            isPlaying = true
        }) {}
    }


    LaunchedEffect(isPlaying) {
        scope.launch {
            if (isPlaying) {

                timeObserver = System.currentTimeMillis()

                while (elapsed < time) {
                    if (isPlaying) {
                        elapsed = (System.currentTimeMillis() - timeObserver).toInt()
                        progress = (elapsed.toFloat() / time)
                        delay(16)
                    } else {

                        break
                    }
                }
            }
        }
    }



    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(10.dp)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    offsetX += dragAmount.x

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

