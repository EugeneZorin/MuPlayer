package com.example.muplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModel
import com.example.domain.usecase.search.SearchAudioContract
import com.example.presentation.screen.MainScreen
import com.example.presentation.screen.StartScreen
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject



@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    @Inject
    lateinit var searchAudioContract: SearchAudioContract



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            MainScreen()
            runBlocking {
                launch {

                }
            }

        }
    }





}


@HiltViewModel
class MyViewModel @Inject constructor(
    private val searchAudioContract: SearchAudioContract,
) : ViewModel() {

    suspend fun execute() {
        searchAudioContract.searchFileContact()
    }
}


