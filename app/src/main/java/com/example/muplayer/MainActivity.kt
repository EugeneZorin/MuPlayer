package com.example.muplayer

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.content.ContextCompat
import com.example.presentation.screen.PlayerScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import android.Manifest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import kotlinx.coroutines.runBlocking


@AndroidEntryPoint
class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val permission = Manifest.permission.READ_EXTERNAL_STORAGE
        val granted = PackageManager.PERMISSION_GRANTED
        ActivityCompat.requestPermissions(this, arrayOf(permission), 123)

        setContent {
            PlayerScreen()
        }


    }






}


/*@HiltViewModel
class MyViewModel @Inject constructor(
    private val searchAudioContract: SearchAudioContract,
) : ViewModel() {

    suspend fun execute() {
        searchAudioContract.searchFileContact()
    }
}*/


