package com.example.muplayer

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModel
import com.example.domain.entity.AlbumEntityModel
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
                    myViewModel.createAlbum("name", albumList)
                    val result = myViewModel.getAll()
                    Log.d("MyLog","SET: $result")
                }
            }

        }
    }

}


@HiltViewModel
class MyViewModel @Inject constructor(
    private val useCaseAlbumContract: UseCaseAlbumContract
): ViewModel() {

    suspend fun createAlbum(id: String, albumList: Map<String, String>?){
        useCaseAlbumContract.createAlbumContract(id = id, albumList = albumList )
    }

    suspend fun search(id: String): Map<String, String>? {
        return useCaseAlbumContract.searchSong(id)
    }

    suspend fun getAll(): List<AlbumEntityModel>{
        return useCaseAlbumContract.getAllAlbums()
    }

}


