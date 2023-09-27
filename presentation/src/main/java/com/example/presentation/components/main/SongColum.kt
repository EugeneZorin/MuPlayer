package com.example.presentation.components.main

import android.util.Log
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.presentation.viewmodels.MainViewModel


@Composable
fun SongColumn(
    it: PaddingValues,
    mainViewModel: MainViewModel = viewModel(),
) {



    Log.d("mainViewModel", "${mainViewModel.allMusic.value}")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(it)
            .padding(horizontal = 17.dp)
            .background(Color.White),
    ) {

        Box(
            modifier = Modifier
                .padding(vertical = 5.dp, horizontal = 5.dp)
        ) {
            Text(
                text = "Произведений: ",
                color = Color.Gray
            )
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            items(100) {
                Box(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(Color(0xFFFBF7F7))
                        .fillMaxWidth()
                        .padding(14.dp),
                ) {
                    Column {
                        Text(text = "Названия песни ")
                        Text(
                            text = "Исполнитель - Неизвестен",
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}