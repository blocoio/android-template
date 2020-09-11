package io.bloco.template.domain

import io.bloco.template.domain.models.Counter
import io.bloco.template.data.CounterRepository
import timber.log.Timber
import javax.inject.Inject

class GetAndSetCounter
@Inject constructor(
    private val preferences: CounterRepository
){

    fun get() = preferences.getValue()

    suspend fun editCounter(counter: Counter) : Result<Counter> {
        if (counter.value < 0) {
            Timber.w(IncrementError(), "Value given was ${counter.value}")
            return Result.failure(IncrementError())
        }
        preferences.setValue(counter)
        return Result.success(counter)
    }

    class IncrementError :
        Exception("Value given to .editCounter() is negative. Negative numbers are not allowed")
}