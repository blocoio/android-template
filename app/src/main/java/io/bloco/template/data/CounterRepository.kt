package io.bloco.template.data

import io.bloco.template.data.models.Counter
import io.bloco.template.data.preferences.CounterStoragePreferences
import javax.inject.Inject

class CounterRepository
@Inject constructor(
    private val preferences: CounterStoragePreferences,
) {
    fun observeValue() = preferences.getValue()
    suspend fun saveValue(value: Counter) = preferences.setValue(value)
}