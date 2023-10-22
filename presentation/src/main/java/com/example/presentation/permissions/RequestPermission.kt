package com.example.presentation.permissions

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import com.example.presentation.viewmodels.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequestPermission(
    mainViewModel: MainViewModel,
    requestPermission: String,
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


    AlertDialog(
        onDismissRequest = mainViewModel::dismissDialog,
        title = { Text("Permission required")},
        confirmButton = {

        }
    )

}

fun Activity.openAppSetting() {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    ).also(::startActivity)
}






