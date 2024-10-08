package com.nezuko.data.repository

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.nezuko.domain.Constants.REQUEST_CODE_CAMERA
import com.nezuko.domain.Constants.REQUEST_CODE_MICRO
import com.nezuko.domain.repository.PermissionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class PermissionRepositoryImpl(
    private val context: Context
) : PermissionRepository {

    private val TAG = "PermissionRepositoryImpl"

    private val _cameraPermissionIsGranted = MutableStateFlow<Boolean>(false)
    override val cameraPermissionIsGranted: StateFlow<Boolean>
        get() = _cameraPermissionIsGranted

    private val _microPermissionIsGranted = MutableStateFlow<Boolean>(false)
    override val microPermissionIsGranted: StateFlow<Boolean>
        get() = _microPermissionIsGranted

    override fun checkCameraPermission() = ContextCompat.checkSelfPermission(
        context, Manifest.permission.CAMERA
    ) == PackageManager.PERMISSION_GRANTED

    override fun checkVoicePermission() = ContextCompat.checkSelfPermission(
        context, Manifest.permission.RECORD_AUDIO
    ) == PackageManager.PERMISSION_GRANTED

    override fun onRequestPermissionsResult(requestCode: Int, grantResult: Boolean) {
        Log.i(TAG, "onRequestPermissionsResult: $requestCode")
        when (requestCode) {
            REQUEST_CODE_CAMERA -> {
                if (_cameraPermissionIsGranted.value != grantResult) {
                    _cameraPermissionIsGranted.update {
                        Log.i(TAG, "onRequestPermissionsResult: notific - $grantResult")
                        grantResult
                    }
                }
            }

            REQUEST_CODE_MICRO -> {
                if (_microPermissionIsGranted.value != grantResult) {
                    _microPermissionIsGranted.update {
                        Log.i(TAG, "onRequestPermissionsResult: audio - $grantResult")
                        grantResult
                    }
                }
            }
        }
    }
}