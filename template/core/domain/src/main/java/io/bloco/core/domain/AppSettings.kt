package io.bloco.core.domain

import io.bloco.core.data.repositories.AppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppSettings @Inject constructor(
    private val appRepository: AppRepository,
) {

    fun isFirstOpening(): Flow<Boolean> {
        return appRepository.isFirstOpening()
    }

    suspend fun appOpened() {
        appRepository.saveFirstTimeOpeningPreference()
    }
}
