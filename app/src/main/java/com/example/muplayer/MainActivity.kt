package com.example.muplayer

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModel
import com.example.domain.usecase.contract.UseCaseAlbumContract
import com.example.domain.usecase.search.FindAllAudioFilesContract
import com.example.domain.usecase.search.SearchAudioContract
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

            runBlocking {
                launch {
                    val albumList: Map<String, String> = mapOf(
                        "key1" to "value1",
                        "key2" to "value2",
                        "key3" to "value3"

                    )

                    val myViewModel: MyViewModel = MyViewModel(searchAudioContract)


                    val result = myViewModel.execute()
                    Log.d("SEE", "SET: $result")


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



