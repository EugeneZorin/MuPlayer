package com.example.presentation.permissions

import android.Manifest
import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import com.example.presentation.viewmodels.MainViewModel

@Composable
fun RequestPermission(
    mainViewModel: MainViewModel,
    requestPermission: String
) {

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            mainViewModel.onPermissionGet(
                permission = requestPermission,
                granted = isGranted
            )
        }
    )

    DisposableEffect(Unit) {
        launcher.launch(requestPermission)
        onDispose { }
    }
}






