package com.example.presentation.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.math.absoluteValue
import kotlin.time.Duration.Companion.days


@Composable
fun PlayerStripe() {


    val context = LocalContext.current
    val player = ExoPlayer.Builder(context).build()
    var isPlaying by remember { mutableStateOf(false) }
    val mediaItem =
        MediaItem.fromUri("/storage/emulated/0/Download/Overlord III - Opening _ VORACITY (320 kbps).mp3")
    val scope = rememberCoroutineScope()

    val state = remember { mutableStateOf(player.currentPosition to player.duration) }


   /* val currentTime: MutableLiveData<Pair<Long, Long>> by lazy {
        MutableLiveData(player.currentPosition to player.duration)
    }*/

    var job: Job? = null



    var offsetX by remember { mutableFloatStateOf(0f) }
    var progress by remember { mutableFloatStateOf(0f) }


   /* LaunchedEffect(isPlaying) {
        if (isPlaying) {
            job = scope.launch {
                while (isPlaying) {
                    progress = player.currentPosition.toFloat() / iterations.toFloat()
                    Log.d("progress", "SET: ${player.currentPosition}")
                    delay(16)
                }
            }
        }  else {
            job?.join()
        }
    }*/

    LaunchedEffect(context) {
        player.addListener(object : Player.Listener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                if (isPlaying) {
                    Log.d("Start", "SET: ${state.value}")
                } else {
                    Log.d("currentPosition", "SET: STOP")
                }
            }
        })

        player.setMediaItem(mediaItem)
        player.prepare()
    }


    IconButton(
        onClick = {
            isPlaying = !isPlaying

            if (isPlaying) {
                player.play()
            } else {
                player.pause()
            }
        }
    ) {
        Icon(
            imageVector = if (isPlaying) Icons.Default.AccountCircle else Icons.Default.PlayArrow,
            contentDescription = if (isPlaying) "Pause" else "Play"
        )
    }

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(10.dp)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    offsetX += dragAmount.x
                    Log.d("progress", "SET: $progress")

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


    /* val delayMillis = 16

     var offsetX by remember { mutableFloatStateOf(0f) }
     val scope = rememberCoroutineScope()
     var iterations = sharedTest.targetTimeMillis / delayMillis
     var job: Job? = null


     var progress =
         player.positionAndDurationState().value.first.toFloat() / player.positionAndDurationState().value.second.absoluteValue

     LaunchedEffect(sharedTest.isPlaying) {
         if (sharedTest.isPlaying) {
             job = scope.launch {
                 for (time in (iterations * progress).toInt() until iterations) {

                     if (sharedTest.isPlaying) {
                         delay(delayMillis.toLong())
                         Log.d("StartProgress", "SET: $progress")
                     }
                 }

             }
         } else {
             job?.cancel()
         }
     }

     Button(onClick = { sharedTest.isPlaying = !sharedTest.isPlaying }) {}

     Canvas(
         modifier = Modifier
             .fillMaxWidth()
             .height(10.dp)
             .pointerInput(Unit) {
                 detectDragGestures { change, dragAmount ->
                     change.consume()
                     offsetX += dragAmount.x
                     Log.d("PLAYER", "SET: $progress")
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

     }*/
}

@Composable
fun Player.positionAndDurationState(): State<Pair<Long, Long>> {

    val state = remember { mutableStateOf(currentPosition to duration) }



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
                reason: Int,
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




