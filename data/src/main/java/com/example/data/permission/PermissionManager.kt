package com.example.data.permission

interface PermissionManager {
    suspend fun requestReadExternalStoragePermission(): Boolean

}