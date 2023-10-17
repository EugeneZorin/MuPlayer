package com.example.presentation.permissions

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import com.example.presentation.viewmodels.MainViewModel

@Composable
fun GetPermission(
    mainViewModel: MainViewModel
) {

    // Request permission fro search audio files
    val readExternalStorage = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {
            mainViewModel.onPermissionGet(
                permission = Manifest.permission.READ_EXTERNAL_STORAGE,
                granted = it
            )
        }
    )
}