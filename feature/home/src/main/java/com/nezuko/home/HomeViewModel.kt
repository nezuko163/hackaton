package com.nezuko.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nezuko.domain.repository.LocalStoreRepository
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val localStoreRepository: LocalStoreRepository
) : ViewModel() {
    private val permissionKey = "shouldShowDataPermission"

    val shouldShowDataPermission = localStoreRepository.preferenceFlow(permissionKey)

    fun edit(value: Boolean) {
        viewModelScope.launch {
            localStoreRepository.savePreference(permissionKey, value)
        }
    }
}