package com.nezuko.domain.repository

import android.app.Activity
import kotlinx.coroutines.flow.StateFlow

interface PermissionRepository {
    val cameraPermissionIsGranted: StateFlow<Boolean>
    val microPermissionIsGranted: StateFlow<Boolean>

    fun checkCameraPermission(): Boolean
    fun checkVoicePermission(): Boolean
    fun onRequestPermissionsResult(requestCode: Int, grantResult: Boolean)
}