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

    suspend fun saveFirstTimeOpeningPreference() {
        dataUserPreferences.edit {
            it[FIRST_OPENING] = false
        }
    }

    fun isFirstOpening(): Flow<Boolean> =
        dataUserPreferences.data.map { preferences ->
            preferences[FIRST_OPENING] ?: true
        }

    companion object {
        private val FIRST_OPENING = booleanPreferencesKey("FIRST_OPENING")
    }
}
