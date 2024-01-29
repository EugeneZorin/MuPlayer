package com.example.presentation.navigation.panel

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.presentation.R
import com.example.presentation.viewmodels.ViewModelPlayList

@Composable
fun MusicInteractionPanel(
    modifier: Modifier = Modifier,
    viewModelPlayList: ViewModelPlayList
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
            IconInteractionPanel(
                icon = painterResource(id = R.drawable.queue_music),
                contentDescription = "Delete",
                text = "Delete",
                alignment = Alignment.Start,
                color = 0xFFF2EDF7,
                viewModelPlayList = viewModelPlayList
            )
            IconInteractionPanel(
                icon = painterResource(id = R.drawable.queue_music),
                contentDescription = "Create",
                text = "Create",
                alignment = Alignment.Start,
                color = 0xFFF2EDF7,
                viewModelPlayList = viewModelPlayList
            )
            IconInteractionPanel(
                icon = painterResource(id = R.drawable.queue_music),
                contentDescription = "Add",
                text = "Add",
                alignment = Alignment.Start,
                color = 0xFFF2EDF7,
                viewModelPlayList = viewModelPlayList
            )

        }
    }

}

@Composable
fun IconInteractionPanel(
    icon: Painter,
    contentDescription: String,
    text: String,
    alignment: Alignment.Horizontal,
    color: Long,
    viewModelPlayList: ViewModelPlayList
) {

    var isDialogVisible by remember { mutableStateOf(false) }
    var enteredText by remember { mutableStateOf("") }

    Button(
        onClick = {
            when (text) {
                "Create" -> {
                    isDialogVisible = true
                }
            }
        },
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

    if (isDialogVisible) {
        AlertDialog(
            onDismissRequest = {
                isDialogVisible = false
            },
            title = {
                Text("Enter playlist name")
            },
            text = {
                TextField(
                    value = enteredText,
                    onValueChange = {
                        enteredText = it
                    },
                    label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                Button(onClick = {
                    isDialogVisible = false
                }) {
                    Text("OK")

                    viewModelPlayList.createPlaylist(namePlayList = enteredText)
                }
            },
            dismissButton = {
                Button(onClick = {
                    isDialogVisible = false
                }) {
                    Text("Cancel")
                }
            }
        )
    }

}