package io.bloco.core.data.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val dataUserPreferences: DataStore<Preferences>
) {

    suspend fun saveHasBeenOpenedPreference() {
        dataUserPreferences.edit {
            it[APP_OPENED] = true
        }
    }

    fun hasBeenOpened(): Flow<Boolean> =
        dataUserPreferences.data.map { preferences ->
            preferences[APP_OPENED] ?: false
        }

    companion object {
        private val APP_OPENED = booleanPreferencesKey("APP_OPENED")
    }
}
