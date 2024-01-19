package com.example.presentation.navigation.panel

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.R


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
                alignment = Alignment.Start,
                color = 0xFFFBF7F7
            )

            IconBottomPanel(
                icon = painterResource(id = R.drawable.playlist_music),
                contentDescription = "Playlist",
                text = "Playlist",
                alignment = Alignment.CenterHorizontally,
                color = 0xFFFBF7F7
            )

            IconBottomPanel(
                icon = painterResource(id = R.drawable.setting_default),
                contentDescription = "Setting",
                text = "Setting",
                alignment = Alignment.End,
                color = 0xFFFBF7F7
            )
        }
    }
}

@Composable
fun MusicInteractionPanel(
    modifier: Modifier = Modifier
) {

    BottomAppBar(
        modifier = modifier
            .fillMaxHeight(0.07f),
        containerColor = Color(0xFFF2EDF7)
    ) {
        Row(
            modifier = modifier
                .fillMaxSize(1f),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically


        ) {
            IconBottomPanel(
                icon = painterResource(id = R.drawable.queue_music),
                contentDescription = "Music",
                text = "Music",
                alignment = Alignment.Start,
                color = 0xFFF2EDF7
            )
            IconBottomPanel(
                icon = painterResource(id = R.drawable.queue_music),
                contentDescription = "Music",
                text = "Music",
                alignment = Alignment.Start,
                color = 0xFFF2EDF7
            )
            IconBottomPanel(
                icon = painterResource(id = R.drawable.queue_music),
                contentDescription = "Music",
                text = "Music",
                alignment = Alignment.Start,
                color = 0xFFF2EDF7
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
    color: Long
) {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(color),
            contentColor = Color.Red),
        interactionSource = remember { MutableInteractionSource() },



    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            Icon(
                painter = icon,
                contentDescription = contentDescription
            )

            Text(
                text = text,
                modifier = Modifier.align(alignment)
            )
        }
    }

}



