package com.example.presentation.components.palylist

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.example.presentation.viewmodels.MainViewModel
import com.example.presentation.viewmodels.ViewModelPlayer
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun PlayerStripe(
    viewModelPlayer: ViewModelPlayer = hiltViewModel(),
) {

    val context = LocalContext.current

    val player = remember { mutableStateOf(ExoPlayer.Builder(context).build()) }

    var isPlaying by remember {
        mutableStateOf(viewModelPlayer.playerStatus.value!!)
    }

    val mediaItem =
        MediaItem.fromUri("/storage/emulated/0/Download/Overlord III - Opening _ VORACITY (320 kbps).mp3")

    val scope = rememberCoroutineScope()

    var job: Job? = null

    var offsetX by remember { mutableFloatStateOf(0f) }
    var progress by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(isPlaying) {
        if (isPlaying) {

            job = scope.launch {
                while (isPlaying) {
                    progress =
                        player.value.currentPosition.toFloat() / player.value.duration.toFloat()
                    delay(16)
                }
            }
        } else {
            job?.join()
        }
    }

    LaunchedEffect(context) {

        player.value.addListener(object : Player.Listener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                if (isPlaying) {
                    Log.d("onIsPlayingChanged", "SET: Start")
                } else {
                    Log.d("onIsPlayingChanged", "SET: STOP")
                }
            }
        })

        player.value.setMediaItem(mediaItem)
        player.value.prepare()
    }


    IconButton(
        onClick = {
            isPlaying = !isPlaying

            if (isPlaying) {
                player.value.play()
            } else {
                player.value.pause()
            }
        }

    ) {
        Icon(
            imageVector = if (isPlaying) Icons.Default.AccountCircle else Icons.Default.PlayArrow,
            contentDescription = if (isPlaying) "Pause" else "Play"
        )
    }

    Text(text = player.value.currentPosition.toString())

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(10.dp)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()

                    offsetX += dragAmount.x

                    player.value.seekTo(((offsetX * player.value.duration) / size.width).toLong())
                }
            },

        ) {


        offsetX = offsetX.coerceIn(0f, size.width)


        drawRect(
            color = Color.Gray,
            size = Size(size.width, 4.dp.toPx()),
        )


        drawCircle(
            color = Color(0xFF982377),
            radius = 30.dp.toPx() / 4,
            center = Offset(
                (size.width * progress).coerceIn(0f, size.width),
                2.dp.toPx() + (4.dp.toPx() - 30.dp.toPx()) / 512
            ),
        )

        drawRect(
            color = Color(0xFF982377),
            size = Size((size.width * progress).coerceIn(0f, size.width), 4.dp.toPx()),
        )

    }
}




