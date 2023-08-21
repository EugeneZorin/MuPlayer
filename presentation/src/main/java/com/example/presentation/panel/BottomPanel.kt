package com.example.presentation.panel

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.screen.components.main.Player


@Preview(showBackground = true)
@Composable
fun BottomPanel() {

    BottomAppBar(
        modifier = Modifier.fillMaxHeight(0.09f),
        containerColor = Color(0xFFFBF7F7),
        contentColor = Color(0xFF797373),
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconBottomPanel(
                icon = painterResource(id = R.drawable.queue_music),
                contentDescription = "Music",
                text = "Music",
                alignment = Alignment.Start
            )

            IconBottomPanel(
                icon = painterResource(id = R.drawable.playlist_music),
                contentDescription = "Playlist",
                text = "Playlist",
                alignment = Alignment.CenterHorizontally
            )

            IconBottomPanel(
                icon = painterResource(id = R.drawable.setting_default),
                contentDescription = "Setting",
                text = "Setting",
                alignment = Alignment.End
            )
        }
    }
}

@Composable
fun IconBottomPanel(
    icon: Painter,
    contentDescription: String,
    text: String,
    alignment: Alignment.Horizontal,
) {
    Column(
        modifier = Modifier.clickable {

        },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = icon,
            contentDescription = contentDescription,
            modifier = Modifier.size(30.dp)
        )

        Text(
            text = text,
            modifier = Modifier.align(alignment)
        )
    }
}



