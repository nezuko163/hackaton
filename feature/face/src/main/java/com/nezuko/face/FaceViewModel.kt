package com.nezuko.face

import androidx.lifecycle.ViewModel
import com.nezuko.domain.repository.PermissionRepository

class FaceViewModel(
    private val permissionRepository: PermissionRepository
) : ViewModel() {

    val cameraPermission = permissionRepository.cameraPermissionIsGranted

    fun onPermissionRequest(requestCode: Int, grantResult: Boolean) {
        permissionRepository.onRequestPermissionsResult(requestCode, grantResult)
    }
}