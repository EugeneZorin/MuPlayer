package com.example.muplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModel
import com.example.domain.usecase.contract.UseCaseAlbumContract
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    @Inject
    lateinit var useCaseAlbumContract: UseCaseAlbumContract


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val myViewModel: MyViewModel = MyViewModel(useCaseAlbumContract)

        setContent {

            runBlocking {
                launch {
                    val albumList: Map<String, String> = mapOf(
                        "key1" to "value1",
                        "key2" to "value2",
                        "key3" to "value3"

                    )


                    myViewModel.execute()



                }
            }

        }
    }


}


@HiltViewModel
class MyViewModel @Inject constructor(
    private val useCaseAlbumContract: UseCaseAlbumContract
) : ViewModel() {

    suspend fun execute(){
        useCaseAlbumContract.getAllAlbums()
    }
}



