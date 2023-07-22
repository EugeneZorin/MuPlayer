package com.example.muplayer

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.data.room.core.CoreEntity
import com.example.data.room.repository.CoreRepositoryImpl
import com.example.domain.entity.CoreEntityModel
import com.example.domain.repository.CoreContract
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var realism: Realism

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {

            runBlocking {
                launch {
                    val r = realism.test()
                    Log.d("SS", "SET: $r")
                }
            }

        }
    }

}


class Realism @Inject constructor(
    private val coreRepositoryImpl: CoreRepositoryImpl
){

    suspend fun test() {
        coreRepositoryImpl.insert(
            CoreEntityModel(
                id = 2,
                nameMusic = "2",
                idMusic = "2"
            )
        )
    }
}