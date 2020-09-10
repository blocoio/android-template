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
    private val errors = BroadcastChannel<Throwable>(1)

    init {
        counterDomain.get()
            .onEach {
            counterCurrentValue.value = it
        }.launchIn(ioScope)

        counterModifier
            .asFlow()
            .onEach { click ->
                var editedValue = counterDomain.get().first()
                when (click) {
                    Modification.Increment -> editedValue++
                    Modification.Decrement -> editedValue--
                }
                counterDomain.editCounter(editedValue)
                    .onFailure { errors.send(it) }
            }.launchIn(ioScope)

    }

    // Inputs
    fun incrementClick() {
        counterModifier.sendBlocking(Modification.Increment)
    }

    fun decrementClick() {
        counterModifier.sendBlocking(Modification.Decrement)
    }

    // OutPuts
    fun getValue(): Flow<Counter> = counterCurrentValue
    fun getErrors(): Flow<Throwable> = errors.asFlow()

    private enum class Modification {
        Increment, Decrement
    }
}







