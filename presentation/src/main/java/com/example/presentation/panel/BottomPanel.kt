package com.example.presentation.panel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.presentation.R


@Composable
fun BottomPanel() {

    BottomAppBar (
        containerColor = Color(0xFFFBF7F7),
        contentColor = Color(0xFF797373)
    ) {
        IconBottomPanel(
            icon = painterResource(id = R.drawable.queue_music),
            contentDescription = "Playlist",
            text = "Playlist"
        )

        IconBottomPanel(
            icon = painterResource(id = R.drawable.queue_music),
            contentDescription = "Playlist",
            text = "Playlist"
        )

        IconBottomPanel(
            icon = painterResource(id = R.drawable.queue_music),
            contentDescription = "Playlist",
            text = "Playlist"
        )

    }

    /*BottomAppBar(
        containerColor = Color(0xFFFBF7F7),
        contentColor = Color(0xFF797373)
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
                    fontSize = 18.sp
                )
            }
            IconButton(
                modifier = Modifier.fillMaxWidth(0.2f),
                onClick = {  }
            ) {
                Text(
                    text = "Home",
                    fontSize = 18.sp
                )
            }
            IconButton(
                modifier = Modifier.fillMaxWidth(0.3f),
                onClick = {  }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.queue_music),
                    contentDescription = "Playlist"
                )

               
            }
        }
    }*/
}


@Composable
fun IconBottomPanel(icon: Painter, contentDescription: String, text: String) {

    IconButton(
        modifier = Modifier
            .fillMaxWidth(0.3f)
            .fillMaxHeight(),
        onClick = { }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = icon,
                contentDescription = contentDescription
            )
            Text(text = text)
        }

    }

}



