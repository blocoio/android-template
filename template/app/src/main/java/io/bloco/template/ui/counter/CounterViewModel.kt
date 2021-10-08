package io.bloco.template.ui.counter

import dagger.hilt.android.lifecycle.HiltViewModel
import io.bloco.template.domain.GetAndSetCounter
import io.bloco.template.domain.models.Counter
import io.bloco.template.ui.BaseViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class CounterViewModel
@Inject constructor(
    counterDomain: GetAndSetCounter
) : BaseViewModel() {

    private val counterModifier = MutableSharedFlow<Modification>(1)

    private val counterCurrentValue = MutableStateFlow(Counter(0))
    private val errors = MutableSharedFlow<Throwable>(1)

    init {
        counterDomain.get()
            .onEach {
                counterCurrentValue.value = it
            }
            .launchIn(ioScope)

        counterModifier
            .onEach { click ->
                var valueToEdit = counterDomain.get().first()

                when (click) {
                    Modification.Increment -> valueToEdit++
                    Modification.Decrement -> valueToEdit--
                }

                counterDomain.editCounter(valueToEdit)
                    .onFailure { errors.emit(it) }
            }
            .launchIn(ioScope)
    }

    // Inputs
    fun incrementClick() = runBlocking { counterModifier.emit(Modification.Increment) }
    fun decrementClick() = runBlocking { counterModifier.emit(Modification.Decrement) }

    // OutPuts
    fun value(): Flow<Counter> = counterCurrentValue
    fun errors(): Flow<Throwable> = errors

    private enum class Modification {
        Increment, Decrement
    }
}