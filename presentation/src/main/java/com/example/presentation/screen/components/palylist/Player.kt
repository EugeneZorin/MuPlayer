package com.example.presentation.screen.components.palylist

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.domain.entity.CoreEntityModel
import com.example.presentation.R
import com.example.presentation.navigation.MainScreens
import com.example.presentation.service.PlayerService
import com.example.presentation.viewmodels.MainViewModel

@Composable
fun Player(
    it: Int,
    quantitiesMusic: State<List<CoreEntityModel>?>,
    navController: NavController,
    context: Context,
    mainViewModel: MainViewModel,
) {

    val externalPlayer = mainViewModel.externalPlayer.observeAsState()
    val intent = Intent(context, PlayerService::class.java)
    val iconResId = externalPlayer.value?.pauseStop ?: true

    val position = it - 1
    val music = quantitiesMusic.value!!

    fun startPlayer(actions: Int){

        mainViewModel.updateExternalData(true)

        when (actions){
            1 ->  intent.action = PlayerService.ACTION_BACK
            2 ->  intent.action = PlayerService.ACTION_PAUSE_PLAY
            3 ->  intent.action = PlayerService.ACTION_NEXT
        }

        context.startService(intent)

    }

    Column(
        modifier = Modifier
            .background(Color(0xFFE1EAF2))
            .fillMaxWidth()
            .clickable {
                navController.navigate(MainScreens.PLAYER_STRIPE)
            },
    ) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {
                Text(text = music[position].nameMusic)
                Text(
                    text = "Performer - Unknown ",
                    color = Color.Gray
                )
            }

            Row(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.End
            ) {


                Box {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_skip_previous_24),
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                            .clickable {
                                startPlayer(1)
                            }
                    )
                }

                Box(
                    modifier = Modifier
                        .padding(horizontal = 20.dp),
                ) {
                    Icon(
                        painter = painterResource(id = if (iconResId) R.drawable.baseline_pause else R.drawable.baseline_play),
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                            .clickable {
                                startPlayer(2)
                            }
                    )
                }

                Box {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_skip_next),
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                            .clickable {
                                startPlayer(3)
                            }
                    )
                }
            }
        }
    }


}












