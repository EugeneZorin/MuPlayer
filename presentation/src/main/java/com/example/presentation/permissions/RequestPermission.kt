package com.example.presentation.permissions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
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


    Log.d("permission", "${shouldShowRequestPermissionRationale(context, requestPermission)}")

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            mainViewModel.onPermissionGet(
                permission = requestPermission,
                granted = isGranted
            )
        }
    )

    DisposableEffect(Unit){
        launcher.launch(requestPermission)
        onDispose {  }
    }


    dialog.reversed().forEach {
        AlertDialog(
            onDismissRequest = mainViewModel::dismissDialog,
            title = { Text("Permission required") },
            confirmButton = {
                Divider()
                Text(
                    text = if (!shouldShowRequestPermissionRationale(context, it)) {
                        "Grant permission"
                    } else {
                        "OK"
                    },
                    fontWeight = FontWeight.Bold,
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
        )
    }

}

fun Activity.openAppSetting() {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    ).also(::startActivity)
}






