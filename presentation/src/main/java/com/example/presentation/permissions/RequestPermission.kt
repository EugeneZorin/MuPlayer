package com.example.presentation.permissions

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.navigation.NavController
import com.example.presentation.navigation.MainScreens
import com.example.presentation.viewmodels.MainViewModel

@Composable
fun RequestPermission(
    mainViewModel: MainViewModel,
    requestPermission: String,
    navController: NavController,
) {

    val context = LocalContext.current as Activity
    val dialog = mainViewModel.permissions


    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            mainViewModel.onPermissionGet(
                permission = requestPermission,
                granted = isGranted
            )
            if (isGranted) {
                navController.navigate(MainScreens.MAIN_SCREE)
            }


        }
    )

    DisposableEffect(Unit){
        launcher.launch(requestPermission)
        onDispose {  }
    }

    dialog.reversed().forEach {
        AlertDialog(
            onDismissRequest = mainViewModel::dismissDialog,
            title = {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        textAlign = TextAlign.Center,
                        text = "Permission required",
                    )
                }
            },
            confirmButton = {
                Text(
                    text = if (!shouldShowRequestPermissionRationale(context, it)) {
                        "Grant permission"
                    } else {
                        "OK"
                    },
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            if (!shouldShowRequestPermissionRationale(context, it)) {
                                context.openAppSetting()
                            } else {
                                launcher.launch(requestPermission)
                            }
                        }
                        .padding(16.dp)
                )
            },
            text = {
                Text(
                    text = if(!shouldShowRequestPermissionRationale(context, it)){
                        "Applications need permissions to start searching for audio files on the device"
                    } else {
                        "Applications need permissions to start searching for audio files on the device"
                    },
                    textAlign = TextAlign.Center
                )
            }
        )
    }
}





fun Activity.openAppSetting() {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    ).also(::startActivity)
}









