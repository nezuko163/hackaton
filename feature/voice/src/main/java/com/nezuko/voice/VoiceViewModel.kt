package com.nezuko.voice

import androidx.lifecycle.ViewModel
import com.nezuko.domain.repository.PermissionRepository

class VoiceViewModel(
    private val permissionRepository: PermissionRepository
) : ViewModel() {
    val microPermission = permissionRepository.microPermissionIsGranted

    fun onPermissionRequest(requestCode: Int, grantResult: Boolean) {
        permissionRepository.onRequestPermissionsResult(requestCode, grantResult)
    }
}