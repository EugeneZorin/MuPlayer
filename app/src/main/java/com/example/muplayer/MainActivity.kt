package com.example.muplayer

import android.content.ContentResolver
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModel
import com.example.domain.entity.AlbumEntityModel
import com.example.domain.usecase.contract.UseCaseAlbumContract
import com.example.domain.usecase.search.SearchFilePresentation
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    @Inject
    lateinit var searchFilePresentation: SearchFilePresentation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val myViewModel: MyViewModel = MyViewModel(searchFilePresentation)

        setContent {

            runBlocking {
                launch {
                    val albumList: Map<String, String> = mapOf(
                        "key1" to "value1",
                        "key2" to "value2",
                        "key3" to "value3"

                    )
                    /*myViewModel.createAlbum("name", albumList)
                    val result = myViewModel.getAll()*/
                   /* Log.d("MyLog","SET: $result")*/


                    val result = myViewModel.test()
                    Log.d("SEE", "SET: $result")

                }
            }

        }
    }

}


@HiltViewModel
class MyViewModel @Inject constructor(
    private val searchFilePresentation: SearchFilePresentation
): ViewModel() {

    suspend fun test(){
        searchFilePresentation.searchFileContact()
    }


}



