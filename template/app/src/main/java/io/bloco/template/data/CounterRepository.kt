package io.bloco.template.data

import com.tfcporciuncula.flow.FlowSharedPreferences
import io.bloco.template.domain.models.Counter
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Provider

class CounterRepository
@Inject constructor(
    private val preferences: Provider<FlowSharedPreferences>
) {

    private val counterPreferences by lazy {
        preferences.get().getInt(COUNTER_KEY, 0)
    }

    fun getValue() = { counterPreferences }.asFlow().flatMapConcat { it.asFlow() }.map { Counter(it) }
    suspend fun setValue(counter: Counter) { counterPreferences.setAndCommit(counter.value) }

    companion object {
        const val COUNTER_KEY : String = "counter_value"
    }
}