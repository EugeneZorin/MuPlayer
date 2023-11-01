package com.example.presentation.components.palylist

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.domain.entity.CoreEntityModel
import com.example.presentation.R

@Composable
fun Player(
    it: Int,
    quantitiesMusic: State<List<CoreEntityModel>?>
) {


    Column(
        modifier = Modifier
            .background(Color(0xFFE1EAF2))
            .fillMaxWidth(),
    ) {



        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {
                Text(text = quantitiesMusic.value!![it].nameMusic)
                Text(
                    text = "Performer - Unknown",
                    color = Color.Gray
                )
            }

            Row(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.End
            ) {


                Box(
                    modifier = Modifier
                        .padding(horizontal = 20.dp),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_pause),
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                            .clickable { }
                    )
                }

                Box {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_skip_next),
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                            .clickable { }
                    )
                }
            }
        }
    }
}












