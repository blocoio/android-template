package io.bloco.template.ui.counter

import io.bloco.template.data.models.Counter
import io.bloco.template.domain.GetAndSetCounter
import io.bloco.template.ui.BaseViewModel
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class CounterViewModel
@Inject constructor(
    counterDomain: GetAndSetCounter
) : BaseViewModel() {

    private val counterModifier = BroadcastChannel<Modification>(1)

    private val counterCurrentValue = MutableStateFlow(Counter(0))
    private val errors = BroadcastChannel<String>(1)

    init {
        counterDomain.get()
            .onEach {
            counterCurrentValue.value = it
        }.launchIn(ioScope)

        counterModifier
            .asFlow()
            .onEach { click ->
                when (click) {
                    Modification.Increment -> counterDomain.editCounter(
                        counterDomain.get().first() + 1
                    )
                    Modification.Decrement -> counterDomain.editCounter(
                        counterDomain.get().first() - 1
                    ).onFailure { errors.send(it.message ?: "Decrement failed") }
                }
            }.launchIn(ioScope)

    }

    // Inputs
    fun incrementCounter() {
        counterModifier.sendBlocking(Modification.Increment)
    }

    fun decrementCounter() {
        counterModifier.sendBlocking(Modification.Decrement)
    }

    // OutPuts
    fun getValue(): StateFlow<Counter> = counterCurrentValue
    fun getErrors(): Flow<String> = errors.asFlow()

    private enum class Modification {
        Increment, Decrement
    }
}







