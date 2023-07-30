package com.example.muplayer

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.room.core.CoreEntity
import com.example.data.room.repository.AlbumRepositoryImpl
import com.example.data.room.repository.CoreRepositoryImpl
import com.example.domain.entity.AlbumEntityModel
import com.example.domain.entity.CoreEntityModel
import com.example.domain.repository.CoreContract
import com.example.domain.usecase.contract.CreateAlbumContract
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    @Inject
    lateinit var createAlbumContract: CreateAlbumContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val myViewModel: MyViewModel = MyViewModel(createAlbumContract)

        setContent {

            runBlocking {
                launch {
                    val albumList: Map<String, String> = mapOf(
                        "key1" to "value1",
                        "key2" to "value2",
                        "key3" to "value3"

                    )
                    myViewModel.createAlbum("name", albumList)
                }
            }

        }
    }

}


@HiltViewModel
class MyViewModel @Inject constructor(
    private val createAlbumContract: CreateAlbumContract
): ViewModel() {

    suspend fun createAlbum(id: String, albumList: Map<String, String>?){
        createAlbumContract.createAlbumContract(id = id, albumList = albumList )
    }

}


