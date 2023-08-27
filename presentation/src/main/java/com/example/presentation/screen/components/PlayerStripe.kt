package com.example.presentation.screen.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun PlayerStripe(progress: Float, onProgressChanged: (Float) -> Unit) {

    var time = 10000
    val offsetX = remember { mutableFloatStateOf(0f) }
    val animatedProgress = remember { Animatable(initialValue = 0f) }
    val scope = rememberCoroutineScope()
    val previousProgress by rememberUpdatedState(progress)

    LaunchedEffect(progress) {
        animatedProgress.stop()
        animatedProgress.snapTo(0f)
        scope.launch {
            animatedProgress.animateTo(
                targetValue = 1f,
                initialVelocity = if (previousProgress < progress) 1f else -1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = time),
                    repeatMode = RepeatMode.Restart
                )
            )
        }
    }


    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(10.dp)
            .pointerInput(Unit) {
                detectDragGestures { _, pan ->
                    offsetX.floatValue += pan.x
                    val newProgress = (offsetX.floatValue / size.width).coerceIn(0f, 1f)
                    onProgressChanged(newProgress)
                }
            }
    ) {


        val lineHeight = 4.dp.toPx()
        val circleSize = 30.dp.toPx()



        val progressWidth = (size.width * (animatedProgress.value + progress)).coerceIn(0f, size.width)

        val circleX = progressWidth.coerceIn(0f, size.width)
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

