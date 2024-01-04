package com.example.presentation.screen.components.palylist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun PlayerStripe() {


    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(text = "PlayerStripe")
        }
    }
    /*val context = LocalContext.current

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

    }*/
}




