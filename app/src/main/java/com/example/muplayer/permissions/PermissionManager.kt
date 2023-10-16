package com.example.muplayer.permissions

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.muplayer.permissions.PermissionEntity.READ_EXTERNAL_STORAGE_CODE

class PermissionManager (
    private val  context: Context,
){

    suspend fun permissionManager(){
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)==
            PackageManager.PERMISSION_GRANTED) {

        } else {
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                READ_EXTERNAL_STORAGE_CODE
            )
        }
    }

    fun askUser(){
        val appSetting = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", context.packageName, null)
        )
        if (context.packageManager.resolveService(appSetting, PackageManager.MATCH_DEFAULT_ONLY ) == null) {
            Toast.makeText(context, "Permissions are denied forever", Toast.LENGTH_SHORT).show()
        } else {
            AlertDialog.Builder(context).setTitle("Permissions").setPositiveButton("Open") { _, _ ->
                context.startActivity(appSetting)

            }.create().show()
        }
    }






}