package com.example.trainingcomposeandcleanarchitecture.data.manger

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.trainingcomposeandcleanarchitecture.domain.manger.LocalUserManger
import com.example.trainingcomposeandcleanarchitecture.util.Constants
import com.example.trainingcomposeandcleanarchitecture.util.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow
import java.util.prefs.Preferences

class LocalUserMangerImpl(
    private val context: Context
): LocalUserManger {
    override suspend fun saveEntry() {
        TODO("Not yet implemented")
    }

    override fun readAppEntry(): Flow<Boolean> {
        TODO("Not yet implemented")
    }
}

private val readOnlyProperty = preferencesDataStore(name = USER_SETTINGS)

val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences> by readOnlyProperty

private object PreferenceKeys {
    val APP_ENTRY = booleanPreferencesKey(Constants.APP_ENTRY)
}