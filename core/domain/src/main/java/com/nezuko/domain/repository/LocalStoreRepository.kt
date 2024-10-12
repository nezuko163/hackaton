package com.nezuko.domain.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface LocalStoreRepository {
    suspend fun savePreference(key: String, value: Boolean)
    fun preferenceFlow(key: String): Flow<Boolean>
}