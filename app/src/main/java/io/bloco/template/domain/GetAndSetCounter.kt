package io.bloco.template.domain

import io.bloco.template.data.CounterRepository
import io.bloco.template.data.models.Counter
import kotlinx.coroutines.flow.*
import java.lang.IllegalArgumentException
import javax.inject.Inject


class GetAndSetCounter
@Inject constructor(
    private val repository: CounterRepository
){

    fun get() = repository.observeValue()

    suspend fun editCounter(counter: Counter) : Result<Counter> {
        if (counter.value < 0) {
            return Result.failure(IllegalArgumentException("Only numbers above 0 are allowed"))
        }
        repository.saveValue(counter)
        return Result.success(counter)
    }

}