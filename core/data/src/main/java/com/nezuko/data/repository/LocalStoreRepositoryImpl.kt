package com.nezuko.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.nezuko.domain.repository.LocalStoreRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class LocalStoreRepositoryImpl(
    private val context: Context
) : LocalStoreRepository {

    private val Context.dataStore by preferencesDataStore(name = "settings")


    override suspend fun savePreference(key: String, value: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[stringPreferencesKey(key)] = if (value) "1" else "0"
        }
    }

    override fun preferenceFlow(key: String): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            val str = preferences[stringPreferencesKey(key)] ?: "0"
            str == "1"
        }
    }
}