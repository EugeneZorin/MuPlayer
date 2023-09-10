package com.example.presentation.screen.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.example.presentation.screen.components.main.Player
import com.example.presentation.viewmodel.ShowAllMusicViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine



@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun PlayerStripe(
    showAllMusicViewModel: ShowAllMusicViewModel = viewModel(),
)  {


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

                for (time in moveProgress until iterations) {

                    moveProgress = (iterations * progress).toInt()

                    if (isPaused) {
                        progress = time.toFloat() / iterations.toFloat()
                        delay(delayMillis.toLong())
                    }

                    val list = showAllMusicViewModel.showAllMusic()
                    Log.d("Coroutines", "SET: $list")

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
            .size(100.dp)
            .drawBehind {
                drawLine(
                    color = Color.Gray,
                    start = Offset(x = 0f, y = 1.dp.toPx()),
                    end = Offset(x = size.width * progress, y = 1.dp.toPx()),
                    strokeWidth = 2.dp.toPx()
                )
            }
    ) {

    }


    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(10.dp)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    offsetX += dragAmount.x

                    moveProgress = (offsetX / size.width)
                        .coerceIn(0f, 1f)
                        .toInt()
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

@Composable
fun Player.positionAndDurationState(): State<Pair<Long, Long>> {

    val state = remember {
        mutableStateOf(currentPosition to duration)
    }


    LaunchedEffect(this) {
        var isSeeking = false

        val listener = object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                if (playbackState == Player.STATE_READY) {
                    isSeeking = false
                }
            }

            override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                state.value = currentPosition to state.value.second
            }

            override fun onPositionDiscontinuity(
                oldPosition: Player.PositionInfo,
                newPosition: Player.PositionInfo,
                reason: Int
            ) {
                if (reason == Player.DISCONTINUITY_REASON_SEEK) {
                    isSeeking = true
                    state.value = currentPosition to duration
                }
            }
        }

        addListener(listener)

        val pollJob = launch {
            while (isActive) {
                delay(500)
                if (!isSeeking) {
                    state.value = currentPosition to duration
                }
            }
        }

        try {
            suspendCancellableCoroutine<Nothing> { }
        } finally {
            pollJob.cancel()
            removeListener(listener)
        }
    }

    return state
}




