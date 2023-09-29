package com.example.presentation.components.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.presentation.viewmodels.MainViewModel


@Composable
fun SongColumn(
    modifier: Modifier = Modifier,
    it: PaddingValues,
    mainViewModel: MainViewModel = viewModel(),
) {

    val quantitiesMusic = mainViewModel.allMusic.observeAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(it)
            .padding(horizontal = 17.dp)
            .background(Color.White),
    ) {

        Box(
            modifier = modifier
                .padding(vertical = 5.dp, horizontal = 5.dp)
        ) {
            Text(
                text = "Works: ${quantitiesMusic.value?.size}",
                color = Color.Gray
            )
        }

        if (quantitiesMusic.value?.size == 0) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(5.dp),
            ) {
                items(quantitiesMusic.value!!.size) {
                    Box(
                        modifier = modifier
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(Color(0xFFFBF7F7))
                            .fillMaxWidth()
                            .padding(14.dp),
                    ) {
                        Column {
                            Text(text = quantitiesMusic.value!![it].nameMusic)
                            Text(
                                text = "Performer - Unknown",
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
        } else {
            Box(
                modifier = modifier
                    .fillMaxSize()
                   ,
            ) {
                Box(modifier = modifier
                    .align(Alignment.Center))
                {
                    Text(text = "Music not found")
                }
            }
        }
    }
}